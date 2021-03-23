package com.cg.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.bean.Customer;
import com.cg.bean.Product;
import com.cg.dao.ICustomerRepository;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.ProductNotFoundException;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	ProductServiceImpl productService;
	
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
	
	//For Getting Products--------------------------------------------------------------------
	public List<Product> getByCategory(String categoryName) throws EmptyInventoryException{		
		return productService.getbyCategory(categoryName);
	}
	public List<Product> getProductsByName(String categoryName, String productName) {		
		List<Product> categoryList = productService.getbyCategory(categoryName);
		List<Product> resultList = categoryList.stream().filter(p->p.getName().equalsIgnoreCase(productName)).collect(Collectors.toList());
		return resultList;
	}

	public List<Product>getProductsBySize(String categoryName, String size) throws ProductNotFoundException{
		List<Product> categoryList = productService.getbyCategory(categoryName);
		List<Product> resultList = categoryList.stream().filter(p->p.getSize().equalsIgnoreCase(size)).collect(Collectors.toList());
		return resultList;
	}

	public List<Product>getProductsByPrice(String categoryName, double mrp) throws ProductNotFoundException{
		List<Product> categoryList = productService.getbyCategory(categoryName);
		List<Product> resultList = categoryList.stream().filter(p->p.getMrp()==mrp).collect(Collectors.toList());
		return resultList;
	}

	public List<Product>getProductsByColor(String categoryName, String color) throws ProductNotFoundException{
		List<Product> categoryList = productService.getbyCategory(categoryName);
		List<Product> resultList = categoryList.stream().filter(p->p.getColor().equalsIgnoreCase(color)).collect(Collectors.toList());
		return resultList;
	}
}