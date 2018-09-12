package com.example.medico.api;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medico.dao.MedicineDao;
import com.example.medico.model.Medicine;
import com.example.medico.utils.Constants;

@RestController 
@CrossOrigin
@RequestMapping("/api")
public class SearchApi {

	@Autowired
	MedicineDao medicineDao;
	@RequestMapping("/search/{page}")
	public List<Medicine> getMedicinesForSearchPage(@PathVariable("page") int page,
									@RequestHeader HttpHeaders headers){
		
		for(Entry<String, List<String>> entry:headers.entrySet()) {
			System.err.println(entry.getKey()+"-------"+entry.getValue());
		}
		if(page>1)
			page = (page*Constants.ROWS)+1;
		return medicineDao.getMedicinePaginated(page,Constants.ROWS);
	}
	
	@RequestMapping("/search/{search}/{page}")
	public List<Medicine> getMedicinesForSearchPage(@PathVariable("page") int page,@PathVariable("search") String search){	
		if(page>1)
			page = (page*Constants.ROWS)+1;
		return medicineDao.getMedicinesOnSearch(search, page,Constants.ROWS);
	}
	
	@RequestMapping("/medicine/{id}")
	public Medicine getMedicineOnId(@PathVariable("id") Long id) {
		return medicineDao.findOne(id);
	}
	
	
	@ExceptionHandler
	public void globalException(Exception e) {
		e.printStackTrace();
	}
}
