package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Customer;
import com.cg.dao.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepo;
	
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public Customer removeCustomer(long custId) {
		Customer customerToBeRemoved = getCustomer(custId);
		customerRepo.deleteById(custId);
		return customerToBeRemoved;
	}

	public Customer updateCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public Customer getCustomer(long userId) {
		Optional<Customer> custOptional = customerRepo.findById(userId);
		return custOptional.isEmpty() ? null : custOptional.get();
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customerList = (List<Customer>) customerRepo.findAll();
		return customerList;
	}
}
