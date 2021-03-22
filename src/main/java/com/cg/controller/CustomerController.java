package com.cg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Customer;
import com.cg.bean.Product;
import com.cg.bean.User;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.ProductNotFoundException;
import com.cg.service.ICustomerService;
import com.cg.service.IProductService;
import com.cg.service.IUserService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IProductService productService;
	
	// WORKING --
	// Register a new customer 
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {	
		return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
	}
	
	// WORKING --
	// Customer sign in
	@PostMapping("/signin")
	public ResponseEntity<User> customerSignIn(@RequestBody User user) {	
		return new ResponseEntity<User>(userService.signIn(user), HttpStatus.OK);
	}
	
	// WORKING --
	// Customer sign out
	@PostMapping("/signout")
	public ResponseEntity<User> customerSignOut(@RequestBody User user) {	
		return new ResponseEntity<User>(userService.signOut(user), HttpStatus.OK);
	}
	
	// WORKING --
	// Update customer details 
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) throws CustomerNotFoundException {
		return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK); 	
	}
	
	// WORKING --
	// Remove a customer
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) throws CustomerNotFoundException {
		return new ResponseEntity<>(customerService.removeCustomer(id), HttpStatus.OK);	
	}
	//-------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/catalogue")
	public ResponseEntity<List<Product>> getAllProducts() throws EmptyInventoryException{
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/productcategory/{categoryName}")
	public ResponseEntity<List<Product>>getbyCategory(@PathVariable String categoryName) {
		List<Product> categoryList = productService.getbyCategory(categoryName);
		return new ResponseEntity<List<Product>>(categoryList, HttpStatus.OK);
	}
	@GetMapping("/productcategory/{categoryName}/getByName/{productName}")
	public ResponseEntity<List<Product>>getByName(@PathVariable String categoryName, @PathVariable String productName) throws ProductNotFoundException{
		List<Product> categoryList = productService.getbyCategory(categoryName);
		List<Product> resultList = categoryList.stream().filter(p->p.getName().equalsIgnoreCase(productName)).collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(resultList, HttpStatus.OK);
	}

	@GetMapping("/productcategory/{categoryName}/getBySize/{size}")
	public ResponseEntity<List<Product>>getBySize(@PathVariable String categoryName, @PathVariable String size) throws ProductNotFoundException{
		List<Product> categoryList = productService.getbyCategory(categoryName);
		List<Product> resultList = categoryList.stream().filter(p->p.getSize().equalsIgnoreCase(size)).collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(resultList, HttpStatus.OK);
	}

	@GetMapping("/productcategory/{categoryName}/getByPrice/{mrp}")
	public ResponseEntity<List<Product>>getByPrice(@PathVariable String categoryName, @PathVariable double mrp) throws ProductNotFoundException{
		List<Product> categoryList = productService.getbyCategory(categoryName);
		List<Product> resultList = categoryList.stream().filter(p->p.getMrp()==mrp).collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(resultList, HttpStatus.OK);
	}
	@GetMapping("/productcategory/{categoryName}/getByColor/{color}")
	public ResponseEntity<List<Product>>getByColor(@PathVariable String categoryName, @PathVariable String color) throws ProductNotFoundException{
		List<Product> categoryList = productService.getbyCategory(categoryName);
		List<Product> resultList = categoryList.stream().filter(p->p.getColor().equalsIgnoreCase(color)).collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(resultList, HttpStatus.OK);
	}
}