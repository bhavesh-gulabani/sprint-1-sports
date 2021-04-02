package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Payment;
import com.cg.dao.IPaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	IPaymentRepository paymentRepository;
	
	@Autowired
	IUserService userService;
	
	@Override
	public Payment addPayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public Payment removePayment(long id) {
		Payment paymentToBeRemoved = getPaymentDetails(id);
		paymentRepository.deleteById(id);
		return paymentToBeRemoved;
	}

	@Override
	public Payment updatePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public Payment getPaymentDetails(long id) {
		Optional<Payment> paymentOptional = paymentRepository.findById(id);
		return paymentOptional.isEmpty() ? null : paymentOptional.get();
	}

	@Override
	public List<Payment> getAllPaymentDetails() {
		List<Payment> paymentList = (List<Payment>) paymentRepository.findAll();
		return paymentList;
	}
}