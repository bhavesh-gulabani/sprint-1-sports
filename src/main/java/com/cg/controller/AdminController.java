//Exception handling in service layer - responsibility of service layer

package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Customer;
import com.cg.bean.Product;
import com.cg.exception.CustomerNotFoundException;
import com.cg.service.ICustomerService;
import com.cg.service.IProductService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IProductService productService;
	
	// WORKING
	// Get all customers
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers() {	
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	// WORKING
	// Get a single customer by id
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) throws CustomerNotFoundException { 
		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
	}
	
	// Add a product
	@PostMapping("/admin/addproduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {	
		return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/products")
	public ResponseEntity<List<Product>> getProducts() {	
		List<Product> products = productService.getAllProduct();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
