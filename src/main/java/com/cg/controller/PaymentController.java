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

// Order is posted without payment,
// payment is to be made

// url : /order/{id}/	--> RequestBody : payment
//			(order id)

// Retrieve order object based on id from service
// Retrieve payment object using payment service based on id of service
// order.setPayment(payment)
// orderService.save(order) // which is updated
// retrieve the same object
// and autowire prodRepo
// repo.save(product)
// get keyset having prodId
// iterate through the keyset get product stock
// and subtract the value of map which is quantity
// and at the same time set the new stock

// Patching the product

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
