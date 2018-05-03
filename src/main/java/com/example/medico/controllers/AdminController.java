package com.example.medico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public String get() {
		return "index";
	}
}
