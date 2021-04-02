package com.cg.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cg.bean.Customer;
import com.cg.bean.Order;
import com.cg.bean.Payment;
import com.cg.bean.Product;
import com.cg.exception.ResourceNotFoundException;

@Service
public interface ICustomerService {
	
	// Customer operations
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(long id) throws ResourceNotFoundException;
	public Customer updateCustomer(Customer customer) throws ResourceNotFoundException;
	
	// Administrator operations 
	public Customer getCustomer(long id) throws ResourceNotFoundException;
	public List<Customer> getAllCustomers() throws ResourceNotFoundException; 
	
	// Product Catalog for customer
	public List<Product> getProductsByName(String categoryName, String productName) throws ResourceNotFoundException;
	public List<Product> getProductsBySize(String categoryName, String size) throws ResourceNotFoundException;
	public List<Product> getProductsByPrice(String categoryName, double price) throws ResourceNotFoundException;
	public List<Product> getProductsByColor(String categoryName, String color) throws ResourceNotFoundException;
	public List<Product> getProductsByPriceRange(String categoryName, double minPrice, double maxPrice) throws ResourceNotFoundException;
	
	// Report Generation	
	public Set<Order> getAllOrders(long id) throws ResourceNotFoundException;
	public Order getOrderDetails(long customerId, long orderId) throws ResourceNotFoundException;
	public Payment getPaymentDetails(long customerId, long orderId) throws ResourceNotFoundException;
	public Map<Product, Integer> getCartDetails(long customerId, long orderId) throws ResourceNotFoundException;
}