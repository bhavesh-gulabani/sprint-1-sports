package com.cg.service;

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

	public List<Customer> getAllCustomers() throws ResourceNotFoundException {
		List<Customer> customerList = (List<Customer>) customerRepository.findAll();
		if (customerList.isEmpty())
			throw new ResourceNotFoundException("No customers found in the system");
		return customerList;
	}

	// To get details of all orders for a particular customer
	@Override
	public Set<Order> getAllOrders(long id) throws ResourceNotFoundException {
		return getCustomer(id).getOrders();
	}
	
	// To get details of a single order for a particular customer
	@Override
	public Order getOrderDetails(long customerId, long orderId) throws ResourceNotFoundException {
		return getCustomer(customerId).getOrders()
					.stream()
					.filter(order -> order.getId() == orderId)
					.findFirst()
					.orElseThrow(() -> new ResourceNotFoundException("Order details not found"));
	}

	// To get payment details for a particular customer's order
	@Override
	public Payment getPaymentDetails(long customerId, long orderId) throws ResourceNotFoundException {
		return getCustomer(customerId).getOrders()
					.stream()
					.filter(order -> order.getId() == orderId)
					.map(order -> order.getPayment())
					.findFirst()
					.orElseThrow(() -> new ResourceNotFoundException("Payment details not found"));
	}

	// To get cart details for a particular customer's order
	@Override
	public Map<Product, Integer> getCartDetails(long customerId, long orderId) throws ResourceNotFoundException {
		return getOrderDetails(customerId, orderId).getCart();
	}

	// To get all products of a specific category by name
	@Override
	public List<Product> getProductsByName(String categoryName, String productName) throws ResourceNotFoundException {
		return productService.getProductsByCategory(categoryName)
				.stream()
				.filter(product -> product.getName().equalsIgnoreCase(productName))
				.collect(Collectors.toList());
	}
	
	// To get all products of a specific category by size
	@Override
	public List<Product>getProductsBySize(String categoryName, String size) throws ResourceNotFoundException, ResourceNotFoundException{
		return productService.getProductsByCategory(categoryName)
				.stream()
				.filter(product ->product.getSize().equalsIgnoreCase(size))
				.collect(Collectors.toList());
	}

	// To get all products of a specific category by price
	@Override
	public List<Product>getProductsByPrice(String categoryName, double price) throws ResourceNotFoundException, ResourceNotFoundException{
		return productService.getProductsByCategory(categoryName)
				.stream()
				.filter(product -> product.getPriceAfterDiscount() == price)
				.collect(Collectors.toList());
	}

	// To get all products of a specific category by color
	@Override
	public List<Product>getProductsByColor(String categoryName, String color) throws ResourceNotFoundException, ResourceNotFoundException{
		return productService.getProductsByCategory(categoryName)
				.stream()
				.filter(product -> product.getColor().equalsIgnoreCase(color))
				.collect(Collectors.toList());
	}
	
	// To get all products of a specific category by the price range
	@Override
	public List<Product> getProductsByPriceRange(String categoryName, double minPrice, double maxPrice) throws ResourceNotFoundException {
		return productService.getProductsByCategory(categoryName)
				.stream()
				.filter(product -> product.getPriceAfterDiscount() >= minPrice && product.getPriceAfterDiscount() <= maxPrice)
				.collect(Collectors.toList());
		}
}