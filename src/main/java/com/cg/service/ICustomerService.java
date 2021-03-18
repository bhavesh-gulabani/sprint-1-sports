package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bean.Customer;

@Service
public interface ICustomerService {
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(long custId);
	public Customer updateCustomer(Customer customer);
	public Customer getCustomer(long custId);
	public List<Customer> getAllCustomers(); 
	
	
}
