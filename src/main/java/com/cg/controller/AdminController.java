package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Customer;
import com.cg.service.ICustomerService;

@RestController
public class AdminController {

	@Autowired
	ICustomerService customerService;
	
	// WORKING
	// Get all customers
	@GetMapping("/admin/customers")
	public ResponseEntity<List<Customer>> getCustomers() {	
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	// WORKING
	// Get a single customer by id
	@GetMapping("admin/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
	}
}
