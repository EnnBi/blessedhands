package com.example.medico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.medico.dao.GeneralDao;
import com.example.medico.dao.UserDao;
import com.example.medico.model.User;
import com.example.medico.utils.Constants;

@Controller
@RequestMapping("/operator")
public class OperatorController {

	@Autowired
	UserDao operatorDao;
	@Autowired 
	GeneralDao generalDao;
	@Autowired
	BCryptPasswordEncoder encoder;	
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		
		if(!model.containsAttribute(("operator")))
			model.addAttribute("operator", new User());
		
		if(model.containsAttribute("result")) {
			model.addAttribute("org.springframework.validation.BindingResult.operator",model.asMap().get("result"));
		}
		model.addAttribute("add", true);
		model.addAttribute("operatorList", operatorDao.findAll());
		return "operator";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@ModelAttribute("operator") User operator,BindingResult result,RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("operator", operator);
			redirectAttributes.addFlashAttribute("result", result);
			redirectAttributes.addFlashAttribute("fail","Please enter data properly");
			return "redirect:/operator";
		}
		if(generalDao.isOperatorPresent(operator,true)) {
			redirectAttributes.addFlashAttribute("operator", operator);
			redirectAttributes.addFlashAttribute("fail","Operator with same email already exists");
			return "redirect:/operator";
		}
		operator.setPassword(encoder.encode(operator.getPassword()));
		operator.getRoles().add(generalDao.findRoleOnName(Constants.ROLE_OPERATOR));
		operator.setEnabled(true);
		operatorDao.save(operator);
		redirectAttributes.addFlashAttribute("success","Operator added successfully");
		return "redirect:/operator";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,Model model) {
		
		if(!model.containsAttribute(("operator")))
			model.addAttribute("operator",operatorDao.findOne(id));
		
		if(model.containsAttribute("result")) {
			model.addAttribute("org.springframework.validation.BindingResult.operator",model.asMap().get("result"));
		}
		
		model.addAttribute("operatorList", operatorDao.findAll());
		return "operator";
	}

	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.POST)
	public String update(@ModelAttribute("operator") User operator,BindingResult result,RedirectAttributes redirectAttributes) {
		
		 if(result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("operator", operator);
			 redirectAttributes.addFlashAttribute("result", result);
			 redirectAttributes.addFlashAttribute("fail","Please enter data properly");
			 
			return "redirect:/operator/edit/"+operator.getId();
		 }
		 if(generalDao.isOperatorPresent(operator, false)) {
			 redirectAttributes.addFlashAttribute("operator", operator);
			 redirectAttributes.addFlashAttribute("fail","Operator with same email already exists");
		 }
		operatorDao.save(operator); 
		redirectAttributes.addFlashAttribute("success","Operator updated successfully");
		
		return "redirect:/operator/";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
		
		try {
			operatorDao.delete(id);
			redirectAttributes.addFlashAttribute("success","Operator deleted successfully");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail","Operator cannot be deleted");
		}
		
		return "redirect:/operator";
	}
}
