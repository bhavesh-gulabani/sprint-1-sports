package com.cg.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

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
import com.cg.bean.Order;
import com.cg.bean.Payment;
import com.cg.bean.Product;
import com.cg.bean.User;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.WrongCredentialsException;
import com.cg.exception.EmptyInventoryException;
import com.cg.service.ICustomerService;
import com.cg.service.IProductService;
import com.cg.service.IUserService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IProductService productService;
	 
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {	
		return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<Object> customerSignIn(@RequestBody User user) throws WrongCredentialsException, ResourceNotFoundException {	
		userService.signIn(user);
		return new ResponseEntity<>(user.getUsername() + " successfully signed in", HttpStatus.OK);
	}
	
	@PostMapping("/signout")
	public ResponseEntity<Object> customerSignOut(@RequestBody User user) {	
		userService.signOut(user);
		return new ResponseEntity<>(user.getUsername() + " successfully signed out", HttpStatus.OK);
	}
	 
	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK); 	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.removeCustomer(id), HttpStatus.OK);	
	}
	
	// Product Catalog =>
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() throws EmptyInventoryException{
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/products/categories/{categoryName}")
	public ResponseEntity<List<Product>>getbyCategory(@PathVariable String categoryName) throws EmptyInventoryException, ResourceNotFoundException {
		return new ResponseEntity<>(productService.getProductsByCategory(categoryName), HttpStatus.OK);
	}
	
	@GetMapping("/products/categories/{categoryName}/names/{productName}")
	public ResponseEntity<List<Product>>getByName(@PathVariable String categoryName, @PathVariable String productName) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getProductsByName(categoryName, productName), HttpStatus.OK);
	}

	@GetMapping("/products/categories/{categoryName}/sizes/{size}")
	public ResponseEntity<List<Product>>getBySize(@PathVariable String categoryName, @PathVariable String size) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getProductsBySize(categoryName, size), HttpStatus.OK);
	}

	@GetMapping("/products/categories/{categoryName}/prices/{price}")
	public ResponseEntity<List<Product>>getByPrice(@PathVariable String categoryName, @PathVariable double price) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getProductsByPrice(categoryName, price), HttpStatus.OK);
	}
	
	@GetMapping("/products/categories/{categoryName}/colors/{color}")
	public ResponseEntity<List<Product>>getByColor(@PathVariable String categoryName, @PathVariable String color) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getProductsByColor(categoryName, color), HttpStatus.OK);
	}
	
	@GetMapping("/products/categories/{categoryName}/prices/{minPrice}/{maxPrice}")
	public ResponseEntity<List<Product>>getByPriceRange(@PathVariable String categoryName, @PathVariable double minPrice,@PathVariable double maxPrice ) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getProductsByPriceRange(categoryName, minPrice, maxPrice), HttpStatus.OK);
	}
	
	// Report Generation =>
	@GetMapping("/{id}/orders")
	public ResponseEntity<Set<Order>> getAllOrders(@PathVariable long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getAllOrders(id), HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}/orders/{orderId}")
	public ResponseEntity<Order> getOrderDetails(@PathVariable long customerId, @PathVariable long orderId) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getOrderDetails(customerId, orderId), HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}/orders/{orderId}/cart")
	public ResponseEntity<Map<Product, Integer>> getCartForOrder(@PathVariable long customerId, @PathVariable long orderId) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getCartDetails(customerId, orderId), HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}/orders/{orderId}/payments")
	public ResponseEntity<Payment> getPaymentForOrder(@PathVariable long customerId, @PathVariable long orderId) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getPaymentDetails(customerId, orderId), HttpStatus.OK);
	}
}