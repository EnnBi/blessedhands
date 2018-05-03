package com.example.medico.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.medico.dao.GeneralDao;
import com.example.medico.model.User;

@Controller
@RequestMapping("/operator")
public class DataEntryOperatorController {
	
	@Autowired
	GeneralDao generalDao;
	
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public String dashboard(Model model,HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("medicineList",generalDao.findAllMedicinesOfUser(user));
		return "operatordashboard";
	}
}
