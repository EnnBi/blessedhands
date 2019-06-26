package com.example.medico.controllers;


import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.medico.dao.OrderDao;
import com.example.medico.dao.UserDao;
import com.example.medico.model.Orders;
import com.example.medico.model.User;
import com.example.medico.utils.Constants;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderDao orderDao;
	@Autowired
	UserDao userDao;	
	
	@GetMapping
	public String getOrdersOnStatus(@RequestParam(value="status",required=false) String status,Model model) {
		if(status != null) {
			status = status.substring(0,1).toUpperCase()+status.substring(1);
			model.addAttribute("orders",orderDao.findByStatusOrderByPlacedDateDesc(status));
			model.addAttribute("status",status);
		}
		else {
			model.addAttribute("orders",orderDao.findAll());
			model.addAttribute("status","All");

		}
		return "orders";
	}
	  
	@GetMapping("/{id}")
	public String findByOrderId(@PathVariable("id") String orderId,Model model) {
		model.addAttribute("order",orderDao.findByOrderId(orderId));
		model.addAttribute("deliveringUsers",userDao.findByRolesName(Constants.ROLE_DELIVERY));
		
		return "order";
	}
	
	@PostMapping("/update")
	public String Role(Orders orderform,HttpSession session,@RequestParam("submit") String type) {
		User user = (User) session.getAttribute("user");

		Orders order = orderDao.findOne(orderform.getId());
		if(type.equals("confirm") || type.equals("confirmship")) {
			for(int i=0;i<orderform.getCartItems().size();i++) {
				order.getCartItems().get(i).setAvailable(orderform.getCartItems().get(i).isAvailable());
				if(orderform.getCartItems().get(i).getTextMedicine() != null)
					order.getCartItems().get(i).getTextMedicine().setPrice(orderform.getCartItems().get(i).getTextMedicine().getPrice());			
			}
			
			order.setTotal(orderform.getTotal());
			order.setConfirmedUser(user);
			order.setConfirmedDate(new Date());
			order.setStatus(Constants.CONFRIMED);
			
			if(orderform.getDeliveredUser()!= null) {
				order.setStatus(Constants.SHIPPED);
				order.setShippedUser(user);
				order.setShippedDate(new Date());
				order.setDeliveredUser(orderform.getDeliveredUser());
				
			}
		}
		else if(type.equals("ship")) {
			
			order.setStatus(Constants.SHIPPED);
			order.setShippedUser(user);
			order.setShippedDate(new Date());
			order.setDeliveredUser(orderform.getDeliveredUser());
		}
		else if(type.equals("cancel")) {
			order.setStatus(Constants.CANCELLED);
		}
		orderDao.save(order);
		return "redirect:/orders";
	}
	
	@ExceptionHandler
	public void exception(Exception e) {
		e.printStackTrace();
	}
	
}
