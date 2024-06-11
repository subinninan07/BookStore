package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderInput;
import com.bookstore.serviceImpl.OrderDetailServiceImpl;


@RestController
public class OrderDetailController {
	
	@Autowired
	private OrderDetailServiceImpl orderDetailServiceImpl;
	
	
	
	@PreAuthorize("hasRole('User')")
	@PostMapping({"/placeOrder/{isSingleProductCheckout}"})
	public void placeOrder(@PathVariable(name= "isSingleProductCheckout") boolean isSingleProductCheckout, @RequestBody OrderInput orderInput) {

		orderDetailServiceImpl.placeOrder(orderInput, isSingleProductCheckout);
		
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping({"/getOrderDetails"})
	public List<OrderDetail> getOrderDetails() {

		return orderDetailServiceImpl.getOrderDetails();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping({"/getAllOrderDetails"})
	public List<OrderDetail> getAllOrderDetails() {

		return orderDetailServiceImpl.getAllOrderDetails();
	}

}
