package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Customer;
import com.cg.dao.ICustomerRepository;
import com.cg.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepo;
	
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public Customer removeCustomer(long custId) throws CustomerNotFoundException {
		Customer customerToBeRemoved = getCustomer(custId);
		customerRepo.deleteById(custId);
		return customerToBeRemoved;
	}

	public Customer updateCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public Customer getCustomer(long userId) throws CustomerNotFoundException {
		Optional<Customer> custOptional = customerRepo.findById(userId);
		return custOptional.orElseThrow(() -> new CustomerNotFoundException("Customer details not found"));
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customerList = (List<Customer>) customerRepo.findAll();
		return customerList;
	}
}