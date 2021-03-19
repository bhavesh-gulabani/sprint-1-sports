package com.cg.dao;

import org.springframework.data.repository.CrudRepository;

import com.cg.bean.Payment;

public interface IPaymentRepository extends CrudRepository<Payment, Long> {

}
