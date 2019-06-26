package com.example.medico.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class RestAuthentication implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2)
			throws IOException, ServletException {
		
		System.out.println("i am commencing the request");
		arg1.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		
	}

	@ExceptionHandler
	public void exception(Exception e) {
		e.printStackTrace();
	}
}
