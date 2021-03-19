package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.bean.Order;
import com.cg.service.IOrderService;

@RequestMapping("/order")
public class OrderController extends WebSecurityConfigurerAdapter {
	
	@Autowired
	IOrderService orderService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
       super.configure(http);
       http.csrf().disable();
    }
	
	// Testing cart
	// Add a new order 
	@PostMapping("/addorder")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {	
		return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
	}

	 
	 

}
