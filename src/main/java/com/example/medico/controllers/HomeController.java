package com.example.medico.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.medico.dao.UserDao;
import com.example.medico.model.User;
import com.example.medico.model.VerificationToken;

@Controller
public class HomeController {

	@Autowired
	UserDao userDao;
	
	@RequestMapping(value= {"/","/login"},method=RequestMethod.GET)
	public String get(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	public @ResponseBody String confirmRegistration
	      (WebRequest request, RedirectAttributes model, @RequestParam("token") String token) {
	   
	     
	    VerificationToken verificationToken = userDao.getVerificationToken(token);
	    if (verificationToken == null) {
	        model.addFlashAttribute("message", "Invalid Token");
	        return "redirect:/InvalidToken";
	    }
	     
	    User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	    	 model.addFlashAttribute("message","Sorry Token has been Expired ");
	        return "redirect:/ExpiredToken";
	    } 
	     
	    user.setEnabled(true); 
	    userDao.save(user); 
	    userDao.deleteVerificationToken(verificationToken);
	    model.addFlashAttribute("message","Account Verified Successfully, You can log In now");
	    return "registratered Successfully";
	    //return "redirect:/registrationsuccess"; 
	}
}
