package com.example.medico.api;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.medico.dao.OrderDao;
import com.example.medico.dao.UserDao;
import com.example.medico.model.CartItem;
import com.example.medico.model.Orders;
import com.example.medico.model.User;
import com.example.medico.security.TokenUtils;
import com.example.medico.utils.CommonMethods;
import com.example.medico.utils.Constants;
@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderApi {

	
	@Value("${token.header}")
	private String tokenHeader;

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	OrderDao orderDao;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> saveOrder(@RequestBody Orders order,HttpServletRequest request) {
		
		String token = request.getHeader(tokenHeader);
		String email = this.tokenUtils.getUsernameFromToken(token);
		
		User user = userDao.findByEmail(email);
		for(CartItem cartItem:order.getCartItems()) {
			cartItem.setOrder(order);
			cartItem.setUser(user);
		}
		
		order.setStatus(Constants.PLACED);
		order.getAddress().setUser(user);
		order.setPlacedUser(user);
		order.setOrderId(generateOrderId());
		order.setPlacedDate(new Date());
		if(CommonMethods.saveImage(order.getPrescriptionPhoto(),new File(Constants.PATH+"/"+order.getOrderId()+".jpg")))
				order.setPrescriptionPhoto(order.getOrderId());
		
		
		System.err.println(order.getAddress()+"----------------"+order.getPrescriptionPhoto());
		orderDao.save(order);
		return  new ResponseEntity<>(JSONObject.quote("success"),HttpStatus.OK);
	}
	
	private String  generateOrderId() {
		 long maxid =orderDao.getMaxId()==null?0:orderDao.getMaxId();
		DateTime d = new DateTime();
		return "BH"
				+StringUtils.leftPad(String.valueOf(d.getMonthOfYear()), 2,
						"0") + d.getYear()+String.format("%05d", (maxid+1));
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Orders> getOrderOnId(@PathVariable("id") Orders order) {
		return new ResponseEntity<Orders>(order,HttpStatus.OK);
	}
	
	
	@GetMapping("/delivery")
	public ResponseEntity<List<Orders>> getPendingOrdersOfDeliveryUser(@RequestParam("status") String status,
																		@RequestParam("page") int page,HttpServletRequest request){
		Pageable pageable;
		System.err.println(status+"--------"+page);
		if(status.equals(Constants.SHIPPED))
			 pageable = new PageRequest(page,10,Sort.Direction.ASC,"placedDate");
		else if(status.equals(Constants.DELIVERED)){
			 pageable = new PageRequest(page,10,Sort.Direction.DESC,"deliveredDate");
		}else {
			pageable=new PageRequest(page,10,Sort.Direction.DESC,"cancelledDate");
		}
		
		List<Orders> orders = orderDao.findByStatusAndDeliveredUser(status,getUserFromToken(request),pageable);
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	//Change status of placed order by  placed user OR of shipped order by delivery user 
	@GetMapping("/status")
	public ResponseEntity<Orders> changeOrderStatus(@RequestParam("status") String status,
															@RequestParam("id") String orderId,HttpServletRequest request){
		System.err.println(status+"------here---"+orderId);
		Orders order = orderDao.findByOrderId(orderId);
		try {
			order.setStatus(status);
			if(status.equals(Constants.DELIVERED)) {
				order.setDeliveredDate(new Date());
				order.setDeliveredUser(getUserFromToken(request));
				
			}else {
				order.setCancelledDate(new Date());
				order.setCancelledUser(getUserFromToken(request));
			}
			orderDao.save(order);
			return new ResponseEntity<Orders>(order,HttpStatus.OK);
		}catch(Exception e) {
			System.err.println("--in error====");
			e.printStackTrace();
			return new ResponseEntity<Orders>(order,HttpStatus.BAD_REQUEST);
		}
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
