package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bean.Customer;
import com.cg.exception.CustomerNotFoundException;

@Service
public interface ICustomerService {
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(long id) throws CustomerNotFoundException;
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	public Customer getCustomer(long id) throws CustomerNotFoundException;
	public List<Customer> getAllCustomers(); 
}