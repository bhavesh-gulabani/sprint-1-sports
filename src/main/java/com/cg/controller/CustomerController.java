package com.cg.controller;

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
import com.cg.service.ICustomerService;
import com.cg.service.IUserService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IUserService userService;
	
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
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK); 	
	}
	
	// WORKING --
	// Remove a customer
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.removeCustomer(id), HttpStatus.OK);	
	}
	
	// WORKING --
	// Get all orders for a particular customer
	@GetMapping("/{id}/orders")
	public ResponseEntity<Set<Order>> getAllOrders(@PathVariable long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getAllOrders(id), HttpStatus.OK);
	}
	
	// WORKING --
	// Get order details of a single order for a particular customer
	@GetMapping("/{customerId}/orders/{orderId}")
	public ResponseEntity<Order> getOrderDetails(@PathVariable long customerId, @PathVariable long orderId) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getOrderDetails(customerId, orderId), HttpStatus.OK);
	}
	
	// WORKING --
	// Get cart details for a particular customer's order
	@GetMapping("/{customerId}/orders/{orderId}/cart")
	public ResponseEntity<Map<Product, Integer>> getCartForOrder(@PathVariable long customerId, @PathVariable long orderId) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getCartDetails(customerId, orderId), HttpStatus.OK);
	}
	
	// WORKING --
	// Get payment details for a particular customer's order
	@GetMapping("/{customerId}/orders/{orderId}/payment")
	public ResponseEntity<Payment> getPaymentForOrder(@PathVariable long customerId, @PathVariable long orderId) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.getPaymentDetails(customerId, orderId), HttpStatus.OK);
	}
}