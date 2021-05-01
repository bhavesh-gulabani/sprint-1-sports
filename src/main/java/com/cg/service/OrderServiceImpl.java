package com.cg.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Order;
import com.cg.bean.Product;
import com.cg.dao.IOrderRepository;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.NotEnoughStockException;
import com.cg.exception.ResourceNotFoundException;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository orderRepository;
	
	@Autowired
	IProductService productService;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IPaymentService paymentService;
	
	@Autowired
	ICartService cartService;
	
	public Order addOrder(Order order, long customerId) throws ResourceNotFoundException {
		
		// customer linked
		customerService.getCustomer(customerId).addOrder(order);
		
		// cart linked
		order.setCart(customerService.getCustomer(customerId).getCart());
		
		// amount calculated
		computeTotalAmount(order);
		
		return orderRepository.save(order);
	}

	public Order removeOrder(long id) throws ResourceNotFoundException {
		Order orderToBeRemoved = getOrderDetails(id);
		orderRepository.deleteById(id);
		return orderToBeRemoved;
	}

	public Order updateOrder(Order order, long customerId) throws ResourceNotFoundException {
		getOrderDetails(order.getId());		// to check if order exists
		computeTotalAmount(order);
		customerService.getCustomer(customerId).addOrder(order);
		return orderRepository.save(order);
	}	

	public Order getOrderDetails(long id) throws ResourceNotFoundException{
		Optional<Order> orderOptional = orderRepository.findById(id);
		if (orderOptional.isEmpty())
			throw new ResourceNotFoundException("Order details not found");
		return orderOptional.get();
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orderList = (List<Order>) orderRepository.findAll();
		return orderList;
	}

	@Override
	public Order updateOrder(long id) throws ResourceNotFoundException{
		Order order = getOrderDetails(id);
		return updateOrder(order, order.getCustomer().getId());
	}
	
	// Method that handles business logic for calculating the total order amount
	@Override
	public Order computeTotalAmount(Order order) {
		order.setAmount(order.getCart().getTotalAmount());
		return order;
	}
	
	
	public Order updateProductStock(Order order) throws NotEnoughStockException, ResourceNotFoundException, IncorrectPriceException {
		Map<Product, Integer> items = order.getCart().getItems();
		int productQuantity = 0;
		
		if (items == null)
			return order;
		
		for (Product product : items.keySet()) {
			 
			productQuantity = items.get(product);
			
			if (product.getStock() - productQuantity < 0)
				throw new NotEnoughStockException("Not enough " + product.getName() + " products in the system");
			
			product.setStock(product.getStock() - productQuantity);	
			productService.updateProduct(product.getId(), product);
		}
		return order;	
	}
	
	// Method that handles business logic for reducing the stock of products
	// which have been bought in the current order
	@Override
	public Order completeOrder(long orderId, long customerId, long paymentId) throws ResourceNotFoundException, IncorrectPriceException, NotEnoughStockException {
		Order order = getOrderDetails(orderId);
		
		Order finalOrder = updateProductStock(order);	
		
		// Set status to paid
		paymentService.getPaymentDetails(paymentId).setStatus("Paid");
		
		// Link payment to order
		paymentService.getPaymentDetails(paymentId).addOrder(finalOrder);
		
		return updateOrder(order, customerId);
	}
	
	@Override
	public Order checkProductStock(long id) throws NotEnoughStockException, ResourceNotFoundException {
		Order order = getOrderDetails(id);
		Map<Product, Integer> items = order.getCart().getItems();
		int productQuantity = 0;
		
		if (items == null)
			return order;
		
		for (Product product : items.keySet()) {
			productQuantity = items.get(product);
			if (product.getStock() - productQuantity < 0)
				throw new NotEnoughStockException("Not enough " + product.getName() + " products in the system");	
		}
		return order;
		
	}
}