package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	}
	
	// WORKING
	// Get a single customer by id
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) throws CustomerNotFoundException { 
		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
	}
	
	// WORKING
	// Add a product
	@PostMapping("/products/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {	
		return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
	}
	
	@PutMapping("/products/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK); 	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
		return new ResponseEntity<>(productService.removeProduct(id), HttpStatus.OK);	
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id) {	
		return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
	}
	
	// WORKING
	// Get all products
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {	
		return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
	}
	
	@GetMapping("/products/name/{name}")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name) {	
		return new ResponseEntity<>(productService.getProductsByName(name), HttpStatus.OK);
	}
	
	@GetMapping("/products/size/{size}")
	public ResponseEntity<List<Product>> getProductsBySize(@PathVariable String size) {	
		return new ResponseEntity<>(productService.getProductsBySize(size), HttpStatus.OK);
	}
	
	@GetMapping("/products/price/{price}")
	public ResponseEntity<List<Product>> getProductsByPrice(@PathVariable double price) {	
		return new ResponseEntity<>(productService.getProductsByPrice(price), HttpStatus.OK);
	}
	
	@GetMapping("/products/color/{color}")
	public ResponseEntity<List<Product>> getProductsByColor(@PathVariable String color) {	
		return new ResponseEntity<>(productService.getProductsByColor(color), HttpStatus.OK);
	}
}