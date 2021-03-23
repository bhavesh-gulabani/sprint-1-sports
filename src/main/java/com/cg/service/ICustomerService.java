package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cg.bean.Customer;
import com.cg.bean.Product;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.ProductNotFoundException;

@Service
public interface ICustomerService {
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(long id) throws CustomerNotFoundException;
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	public Customer getCustomer(long id) throws CustomerNotFoundException;
	public List<Customer> getAllCustomers();
	//Methods for product view
	public List<Product>getProductsByName(String categoryName, String productName) ;
	public List<Product>getProductsBySize(String categoryName, String size) throws ProductNotFoundException;
	public List<Product>getProductsByPrice(String categoryName, double mrp) throws ProductNotFoundException;
	public List<Product>getProductsByColor(String categoryName, String color) throws ProductNotFoundException;
}