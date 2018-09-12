package com.example.medico.api;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.medico.api.model.LoginUser;
import com.example.medico.api.model.Token;
import com.example.medico.dao.UserDao;
import com.example.medico.model.Role;
import com.example.medico.model.User;
import com.example.medico.security.TokenUtils;
import com.example.medico.serviceImpl.UserDetailsServiceImpl;
import com.example.medico.utils.Constants;
import com.example.medico.utils.OnRegistrationCompleteEvent;




/**
 * Handles requests for the application home page.
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginService {

	@Autowired
	UserDetailsServiceImpl myUserDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	TokenUtils tokenUtils;

	@Value("${token.secret}")
	private String secret;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> isAuthenticated(@RequestBody LoginUser user, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		// UserDetails u = loginservice.loadUserByUsername(user.getUsername());

		System.out.println(user);
		// Perform the authentication
		Authentication authentication = this.authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
 
		// Reload password post-authentication so we can generate token
		UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(user.getEmail());
		String token = this.tokenUtils.generateToken(userDetails);

		// Return the token
		Token t = new Token();
		t.setToken(token);
		@SuppressWarnings("unchecked")
		Iterator<GrantedAuthority> iterator = (Iterator<GrantedAuthority>) userDetails.getAuthorities().iterator();
		while(iterator.hasNext()) {
			t.getRole().add(iterator.next().getAuthority());
		}
		return new ResponseEntity<>(t, HttpStatus.OK);
		

	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody User user,HttpServletResponse response,HttpServletRequest request){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Role role = new Role();
		role.setName(Constants.ROLE_USER);
	
		user.setEnabled(false);
		user.setRoles(Arrays.asList(role));
		User registered = userDao.save(user);

		try {
	        String appUrl = request.getContextPath();
	        System.err.println(appUrl+"---appurl");
	        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
	          (registered, request.getLocale(), appUrl));
	       } catch (Exception me) {
	    	   System.err.println("-------error in publishing--------");
	    	   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    	
	    /*	userService.deleteVer(registered);
	    	userService.deleteUser(registered);
	    	System.out.println(me);
		    redirectAttributes.addFlashAttribute("fail","Email address cannot be verified or You are not connected to internet");*/
		}
		return new ResponseEntity<>(JSONObject.quote("success"),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/images/{imageName:.+}", method = RequestMethod.GET)
	public void getFaultReportImage(
			@PathVariable("imageName") String imageName, Model model,
			HttpServletRequest req, HttpServletResponse rep) {
		try {
			// MechanicalEquipment e = mechanicalEquipmentService.find(id);
			System.out.println("imgeName=" + imageName);
			InputStream is = new FileInputStream(Constants.PATH+"/" + imageName);

			byte[] bytes = IOUtils.toByteArray(is);
			if (imageName.contains(".pdf"))
				rep.setContentType("application/pdf");
			else if (imageName.contains(".dwg"))
				rep.setContentType("image/vnd.dwg");
			else if (imageName.contains(".gif"))
				rep.setContentType("image/gif");
			else
				rep.setContentType("image/jpeg");
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();

		} catch (Exception e) {// e.printStackTrace();
			System.out.println("Image " + imageName + " not present");
		}
	}
	/*
	@ExceptionHandler
	public void exception(Exception e) {
		e.printStackTrace();
	}
*/
}
