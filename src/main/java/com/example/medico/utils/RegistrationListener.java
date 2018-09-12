package com.example.medico.utils;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.medico.dao.UserDao;
import com.example.medico.model.User;
import com.example.medico.model.VerificationToken;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	@Autowired
	UserDao userDao;
   
    @Autowired 
    private JavaMailSender mailSender;
 
	@Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event)  {
        
			this.confirmRegistration(event);
		
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event)  {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken(token, user);
        userDao.createVerificationToken(verificationToken);
        String recipientAddress = user.getEmail();
        String confirmationUrl ="/registrationConfirm?token=" + token;
        String message ="http://localhost:8080" + confirmationUrl;
        
        MimeMessage mailMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, false,"utf-8");
		   	String text = "Please click on the given link to complete the registration process.<br>"+message;
		   	messageHelper.setText(text,true);
		   	messageHelper.setTo(recipientAddress);
		   	messageHelper.setSubject("Registration Confirm");
			mailSender.send(mailMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }	
 
}
