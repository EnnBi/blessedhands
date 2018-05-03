package com.example.medico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.medico.dao.CompanyDao;
import com.example.medico.model.Company;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyDao companyDao;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		
		model.addAttribute("company", new  Company());
		model.addAttribute("add", true);
		model.addAttribute("companyList",companyDao.findAll());
		return "company";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@ModelAttribute("company") Company company,BindingResult result,
						RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("company", company);
			redirectAttributes.addFlashAttribute("result", result);
			redirectAttributes.addFlashAttribute("fail","Please enter data properly");
			
			return "redirect:/company";
		}
		
		companyDao.save(company);
		redirectAttributes.addFlashAttribute("success","Company added successfully");
		return "redirect:/company";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,Model model) {
		
		model.addAttribute("company",companyDao.findOne(id));
		model.addAttribute("companyList",companyDao.findAll());
		return "company";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.POST)
	public String update(@ModelAttribute("company") Company company,BindingResult result,RedirectAttributes redirectAttributes) {
		
		 if(result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("company", company);
			 redirectAttributes.addFlashAttribute("result", result);
			 redirectAttributes.addFlashAttribute("fail","Please enter data properly");
			 
			return "redirect:/company/edit/"+company.getId();
		 }
		 
		companyDao.save(company); 
		redirectAttributes.addFlashAttribute("success","Company updated successfully");
		
		return "redirect:/company/";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
		
		try {
			companyDao.delete(id);
			redirectAttributes.addFlashAttribute("success","Company deleted successfully");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail","Company cannot be deleted");
		}
		
		return "redirect:/unit";
	}
}
