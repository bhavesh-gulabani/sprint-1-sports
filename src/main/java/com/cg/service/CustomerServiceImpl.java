package com.cg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Customer;
import com.cg.bean.Order;
import com.cg.bean.Payment;
import com.cg.bean.Product;
import com.cg.dao.ICustomerRepository;
import com.cg.exception.ResourceNotFoundException;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	IProductService productService;
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer removeCustomer(long id) throws ResourceNotFoundException {
		Customer customerToBeRemoved = getCustomer(id);
		customerRepository.deleteById(id);
		return customerToBeRemoved;
	}

	public Customer updateCustomer(Customer customer) throws ResourceNotFoundException {
		getCustomer(customer.getId());	// to check whether the customer exists
		return customerRepository.save(customer); 
	}

	public Customer getCustomer(long id) throws ResourceNotFoundException {
		Optional<Customer> custOptional = customerRepository.findById(id);
		return custOptional.orElseThrow(() -> new ResourceNotFoundException("Customer details not found"));
	}

	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	// Method to get all orders for a particular customer
	@Override
	public Set<Order> getAllOrders(long id) throws ResourceNotFoundException {
		return getCustomer(id).getOrders();
	}
	
	// Method to get details of a single order for a particular customer
	@Override
	public Order getOrderDetails(long customerId, long orderId) throws ResourceNotFoundException {
		List<Order> orders =  getCustomer(customerId).getOrders()
								.stream()
								.filter(order -> order.getId() == orderId)
								.collect(Collectors.toList());
		if (orders.isEmpty())
			throw new ResourceNotFoundException("Order details not found");
		return orders.get(0);
	}

	// Method to get payment details for a particular customer's order
	@Override
	public Payment getPaymentDetails(long customerId, long orderId) throws ResourceNotFoundException {
		List<Payment> payments =  getCustomer(customerId).getOrders()
									.stream()
									.filter(order -> order.getId() == orderId)
									.map(order -> order.getPayment())
									.collect(Collectors.toList());
		if (payments.isEmpty())
			throw new ResourceNotFoundException("Payment details not found");
		return payments.get(0);
	}

	// Method to get cart details for a particular customer's order
	@Override
	public Map<Product, Integer> getCartDetails(long customerId, long orderId) throws ResourceNotFoundException {
		Map<Integer, Integer> originalCart = getOrderDetails(customerId, orderId).getCart();
		
		// For better readability of the response we have created a new map of product -> quantity
		Map<Product, Integer> completeCartDetails = new HashMap<>();	
		for (int productId : originalCart.keySet()) {			
			completeCartDetails.put(productService.getProduct(productId), originalCart.get(productId));		
		}
		return completeCartDetails;
	}
}