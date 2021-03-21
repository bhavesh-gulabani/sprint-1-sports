package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Customer;
import com.cg.dao.ICustomerRepository;
import com.cg.exception.CustomerNotFoundException;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepository;
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer removeCustomer(long id) throws CustomerNotFoundException {
		Customer customerToBeRemoved = getCustomer(id);
		customerRepository.deleteById(id);
		return customerToBeRemoved;
	}

	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
		if (customerOptional.isEmpty())
			throw new CustomerNotFoundException("Customer details not found");
			
		return customerRepository.save(customer); 
	}

	public Customer getCustomer(long userId) throws CustomerNotFoundException {
		Optional<Customer> custOptional = customerRepository.findById(userId);
		return custOptional.orElseThrow(() -> new CustomerNotFoundException("Customer details not found"));
	}

	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
}