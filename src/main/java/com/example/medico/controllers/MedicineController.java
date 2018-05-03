package com.example.medico.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.medico.dao.CompanyDao;
import com.example.medico.dao.MedicineDao;
import com.example.medico.dao.TypeDao;
import com.example.medico.dao.UnitDao;
import com.example.medico.model.Medicine;
import com.example.medico.model.User;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	MedicineDao medicineDao;
	@Autowired
	TypeDao typeDao;	
	@Autowired
	CompanyDao companyDao;
	@Autowired
	UnitDao unitDao;
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("medicine", new Medicine());
		model.addAttribute("typeList", typeDao.findAll());
		model.addAttribute("unitList", unitDao.findAll());
		model.addAttribute("companyList", companyDao.findAll());
		return "medicine";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@ModelAttribute("medicine") Medicine medicine,BindingResult result,HttpSession session,
					   @RequestParam(value="files") MultipartFile[] files,RedirectAttributes redirectAttributes) {
		
		User user = (User) session.getAttribute("user");
		
		/*if(files.length == 0) {
			result.rejectValue("images", "images","Please upload images");
		}*/
		
		if(result.hasErrors()) {
			System.err.println("-errorrs----"+result.getAllErrors());
			redirectAttributes.addFlashAttribute("medicine", medicine);
			redirectAttributes.addFlashAttribute("result", result);
			redirectAttributes.addFlashAttribute("fail","Please enter data properly");
			
			return "redirect:/medicine";
		}
		
		/*for(int i=0;i<files.length;i++) {
			String filename = Constants.PATH+"/"+medicine.getName()+i+".jpg";
			try {
				files[i].transferTo(new File(filename));
				medicine.getImages().add(filename);
			} catch (IllegalStateException | IOException e) {
				redirectAttributes.addFlashAttribute("medicine", medicine);
				redirectAttributes.addFlashAttribute("fail","Files cannot be uploaded,Please try again");
				e.printStackTrace();
			}
		}*/
		medicine.setOperator(user);
		medicineDao.save(medicine);
		redirectAttributes.addFlashAttribute("success","Medicine added successfully");
		return "redirect:/medicine";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model) {
		model.addAttribute("medicineList",medicineDao.findAll());
		return "medicinelist";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,Model model) {
		
		model.addAttribute("medicine",medicineDao.findOne(id));
		model.addAttribute("edit", true);
		
		return "medicine";
	}

	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.POST)
	public String update(@ModelAttribute("medicine") Medicine medicine,BindingResult result,RedirectAttributes redirectAttributes) {
		
		 if(result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("medicine", medicine);
			 redirectAttributes.addFlashAttribute("result", result);
			 redirectAttributes.addFlashAttribute("fail","Please enter data properly");
			 
			return "redirect:/medicine/edit/"+medicine.getId();
		 }
		 
		medicineDao.save(medicine); 
		redirectAttributes.addFlashAttribute("success","Medicine updated successfully");
		
		return "redirect:/medicine/";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
		
		try {
			medicineDao.delete(id);
			redirectAttributes.addFlashAttribute("success","Medicine deleted successfully");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail","Medicine cannot be deleted");
		}
		
		return "redirect:/medicine";
	}
	
	@RequestMapping(value="/allmedicines",method=RequestMethod.GET)
	public @ResponseBody List<Medicine> jsonAllMedicines() {
		return (List<Medicine>) medicineDao.findAll();
	} 
}
