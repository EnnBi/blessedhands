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

import com.example.medico.dao.UnitDao;
import com.example.medico.model.Unit;

@Controller
@RequestMapping("/unit")
public class UnitController {

	@Autowired
	UnitDao unitDao;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("unit", new Unit());
		model.addAttribute("add", true);
		model.addAttribute("unitList",unitDao.findAll());

		return "unit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@ModelAttribute("unit") Unit unit,BindingResult result,RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("unit", unit);
			redirectAttributes.addFlashAttribute("result", result);
			redirectAttributes.addFlashAttribute("fail","Please enter data properly");
			
			return "redirect:/unit";
		}
		
		unitDao.save(unit);
		redirectAttributes.addFlashAttribute("success","Unit added successfully");
		return "redirect:/unit";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,Model model) {
		
		model.addAttribute("unit",unitDao.findOne(id));
		model.addAttribute("unitList",unitDao.findAll());
		return "unit";
	}

	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.POST)
	public String update(@ModelAttribute("unit") Unit unit,BindingResult result,RedirectAttributes redirectAttributes) {
		
		 if(result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("unit", unit);
			 redirectAttributes.addFlashAttribute("result", result);
			 redirectAttributes.addFlashAttribute("fail","Please enter data properly");
			 
			return "redirect:/unit/edit/"+unit.getId();
		 }
		 
		unitDao.save(unit); 
		redirectAttributes.addFlashAttribute("success","Unit updated successfully");
		
		return "redirect:/unit/";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
		
		try {
			unitDao.delete(id);
			redirectAttributes.addFlashAttribute("success","Unit deleted successfully");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail","Unit cannot be deleted");
		}
		
		return "redirect:/unit";
	}
}
