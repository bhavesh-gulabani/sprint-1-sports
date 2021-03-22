package com.cg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.Payment;

@Repository
public interface IPaymentRepository extends CrudRepository<Payment, Long> {
}