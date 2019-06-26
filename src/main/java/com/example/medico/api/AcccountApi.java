package com.example.medico.api;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.medico.dao.OrderDao;
import com.example.medico.dao.UserDao;
import com.example.medico.model.Orders;
import com.example.medico.model.User;
import com.example.medico.security.TokenUtils;
import com.example.medico.utils.CommonMethods;
import com.example.medico.utils.Constants;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class AcccountApi {

	@Value("${token.header}")
	private String tokenHeader;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	OrderDao orderDao;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<User> getUser(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		return new ResponseEntity<User>(getUserFromToken(request),HttpStatus.OK);
	}
	
	@RequestMapping(value="/orders",method=RequestMethod.GET)
	public ResponseEntity<List<Orders>> getOrdersList(HttpServletRequest request){
		
		List<Orders> orders = orderDao.findByPlacedUserOrderByPlacedDateDesc(getUserFromToken(request));
		for(Orders order:orders) {
			System.err.println(order.getPlacedUser().getEmail());
		}
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	@RequestMapping(value="/photo",method=RequestMethod.POST)
	public ResponseEntity<String> uploadPhoto(@RequestBody String image,HttpServletRequest request){
		User user = getUserFromToken(request); 
		System.err.println("image from mobile--------"+image);
		if(CommonMethods.saveImage(image, new File(Constants.PATH+"/"+user.getEmail()+".jpg")))
			user.setImage(user.getEmail()+".jpg");
		
		userDao.save(user);
		return new ResponseEntity<>(JSONObject.quote(user.getImage()),HttpStatus.OK);
	}
	
	public User getUserFromToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String email = this.tokenUtils.getUsernameFromToken(token);
		
		return userDao.findByEmail(email);
	}
	

	@ExceptionHandler
	public void Exception(Exception e) {
		e.printStackTrace();
	}
	
} 
