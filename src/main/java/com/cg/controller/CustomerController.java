package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Customer;
import com.cg.bean.User;
import com.cg.service.ICustomerService;
import com.cg.service.IUserService;

@RestController
public class CustomerController extends WebSecurityConfigurerAdapter {
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IUserService userService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       super.configure(http);
       http.csrf().disable();
    }
	
	// WORKING
	// Register a new customer 
	@PostMapping("/customer/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {	
		return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
	}
	
	// Customer sign in
	@PostMapping("/customer/signin")
	public ResponseEntity<User> customerSignIn(@RequestBody User user) {	
		return new ResponseEntity<User>(userService.signIn(user), HttpStatus.OK);
	}
	
	// Customer sign out
	@PostMapping("/customer/signout")
	public ResponseEntity<User> customerSignOut(@RequestBody User user) {	
		return new ResponseEntity<User>(userService.signOut(user), HttpStatus.OK);
	}
	
	// WORKING
	// Update customer details 
	@PutMapping("/customer/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		if (customerService.getCustomer(customer.getId()) != null) {
			return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK); 	
		} else
			return new ResponseEntity<>(customer, HttpStatus.BAD_REQUEST);
	}
	
	// WORKING
	// Remove a customer (DELETE)
	@DeleteMapping("/customer/delete/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) {
		
		Customer removedCustomer = customerService.removeCustomer(id);
		
		return removedCustomer == null ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : 
			new ResponseEntity<>(removedCustomer, HttpStatus.OK);	
	}
	
	
}