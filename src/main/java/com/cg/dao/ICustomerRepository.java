package com.cg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.Customer;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
	
}
