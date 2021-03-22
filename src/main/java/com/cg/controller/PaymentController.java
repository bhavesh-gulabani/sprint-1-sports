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
import com.cg.bean.Payment;
import com.cg.service.IPaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	IPaymentService paymentService;

	 
	@PostMapping("/add")
	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {	
		return new ResponseEntity<>(paymentService.addPayment(payment), HttpStatus.CREATED);
	}
	
	 
	@PutMapping("/update")
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
		return new ResponseEntity<>(paymentService.updatePayment(payment), HttpStatus.OK); 	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Payment> deletePayment(@PathVariable Integer id) {
		return new ResponseEntity<>(paymentService.removePayment(id), HttpStatus.OK);	
	}
	
	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> getPayments() {	
		return new ResponseEntity<>(paymentService.getAllPaymentDetails(), HttpStatus.OK);
	}
	
	@GetMapping("/payments/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable long id) {	
		return new ResponseEntity<>(paymentService.getPaymentDetails(id), HttpStatus.OK);
	}
}