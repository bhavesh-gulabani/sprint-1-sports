package com.cg.dao;

import java.util.List;

import com.cg.bean.Payment;

public interface IPaymentRepository {
	public Payment addPayment(Payment payment);
	public Payment removePayment(long id);
	public Payment updatePayment(long id, Payment payment);
	public Payment getPaymentDetails(long id);
	public List<Payment> getAllPaymentDetails();
}
