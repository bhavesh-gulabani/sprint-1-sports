package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Order;
import com.cg.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	IOrderService orderService;
	
	// Add a new order 
	@PostMapping("/add")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {	
		return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getOrders() {	
		return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) {	
		return new ResponseEntity<>(orderService.updateOrder(order), HttpStatus.OK);
	}
	
	@PatchMapping("/patch")
	public ResponseEntity<Order> patchOrder(@RequestBody Order order) {	
		return new ResponseEntity<>(orderService.updateOrder(order), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable long id) {	
		return new ResponseEntity<>(orderService.removeOrder(id), HttpStatus.OK);
	}
	
	
	 
}
