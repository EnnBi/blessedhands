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

import com.example.medico.dao.TypeDao;
import com.example.medico.model.Type;

@Controller
@RequestMapping("/type")
public class TypeController {

	@Autowired
	TypeDao typeDao;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("type", new Type());
		model.addAttribute("add", true);
		model.addAttribute("typeList",typeDao.findAll());
		return "type";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@ModelAttribute("type") Type type,BindingResult result,RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("type", type);
			redirectAttributes.addFlashAttribute("result", result);
			redirectAttributes.addFlashAttribute("fail","Please enter data properly");
			
			return "redirect:/type";
		}
		
		typeDao.save(type);
		redirectAttributes.addFlashAttribute("success","Type added successfully");
		return "redirect:/type";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,Model model) {
		
		model.addAttribute("type",typeDao.findOne(id));
		model.addAttribute("typeList",typeDao.findAll());
		
		return "type";
	}

	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.POST)
	public String update(@ModelAttribute("type") Type type,BindingResult result,RedirectAttributes redirectAttributes) {
		
		 if(result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("type", type);
			 redirectAttributes.addFlashAttribute("result", result);
			 redirectAttributes.addFlashAttribute("fail","Please enter data properly");
			 
			return "redirect:/type/edit/"+type.getId();
		 }
		 
		typeDao.save(type); 
		redirectAttributes.addFlashAttribute("success","Type updated successfully");
		
		return "redirect:/type/";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
		
		try {
			typeDao.delete(id);
			redirectAttributes.addFlashAttribute("success","Type deleted successfully");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail","Type cannot be deleted");
		}
		
		return "redirect:/type";
	}
}
