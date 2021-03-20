package com.cg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Customer;
import com.cg.bean.User;
import com.cg.exception.CustomerNotFoundException;
import com.cg.service.ICustomerService;
import com.cg.service.IUserService;

@RestController
@RequestMapping("/customer")
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
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {	
		return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
	}
	
	// WORKING
	// Customer sign in
	@PostMapping("/signin")
	public ResponseEntity<User> customerSignIn(@RequestBody User user) {	
		return new ResponseEntity<User>(userService.signIn(user), HttpStatus.OK);
	}
	
	// WORKING
	// Customer sign out
	@PostMapping("/signout")
	public ResponseEntity<User> customerSignOut(@RequestBody User user) {	
		return new ResponseEntity<User>(userService.signOut(user), HttpStatus.OK);
	}
	
	// WORKING
	// Update customer details 
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) throws CustomerNotFoundException {
		return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK); 	
	}
	
	// WORKING
	// Remove a customer
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) throws CustomerNotFoundException {
		return new ResponseEntity<>(customerService.removeCustomer(id), HttpStatus.OK);	
	}

}