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

import com.cg.bean.Order;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.NotEnoughStockException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.service.IOrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	IOrderService orderService;
	
	@PostMapping
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {	
		return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> getOrders() {	
		return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable long id) throws ResourceNotFoundException {	
		return new ResponseEntity<>(orderService.getOrderDetails(id), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) throws ResourceNotFoundException {	
		return new ResponseEntity<>(orderService.updateOrder(order), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable long id) throws ResourceNotFoundException {	
		return new ResponseEntity<>(orderService.removeOrder(id), HttpStatus.OK);
	}
	
	// Controller to handle the payment linking to the order and confirmation of order
	@PutMapping("/confirm")
	public ResponseEntity<Order> confirmOrder(@RequestBody Order order) throws ResourceNotFoundException, IncorrectPriceException, ResourceNotFoundException, NotEnoughStockException {	
		// This will update the product stock in the system
		orderService.confirmOrder(order); 
		return new ResponseEntity<>(orderService.updateOrder(order), HttpStatus.OK);
	}
	
}
