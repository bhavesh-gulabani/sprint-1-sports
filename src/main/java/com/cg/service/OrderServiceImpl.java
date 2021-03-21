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

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository orderRepository;
	
	@Autowired
	IProductService productService;
	
	public Order addOrder(Order order) {
		computeTotalAmount(order);
		return orderRepository.save(order);
	}

	public Order removeOrder(long id) {
		Order orderToBeRemoved = getOrderDetails(id);
		orderRepository.deleteById(id);
		return orderToBeRemoved;
	}

	public Order updateOrder(Order order) {
		computeTotalAmount(order);
		return orderRepository.save(order);
	}	

	public Order getOrderDetails(long id) {
		Optional<Order> orderOptional = orderRepository.findById(id);
		return orderOptional.isEmpty() ? null : orderOptional.get();
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orderList = (List<Order>) orderRepository.findAll();
		return orderList;
	}

	@Override
	public Order updateOrder(long id) {
		Order order = getOrderDetails(id);
		return updateOrder(order);
	}
	
	// Method that handles business logic for calculating the total order amount
	@Override
	public Order computeTotalAmount(Order order) {
		Map<Integer, Integer> cart = order.getCart();
		double totalAmount = 0;
		
		// If no items exist, in the cart then we set the amount to 0
		if (cart == null) {
			order.setAmount(totalAmount);
			return order;
		}
			
		// For every product in the cart, multiply its price by the quantity, 
		// and add to totalAmount
		for (Integer productId : cart.keySet())
			totalAmount += 
					productService.getProduct(productId).getPriceAfterDiscount() * cart.get(productId);

		order.setAmount(totalAmount);
		return order;
	}
	
	// Method that handles business logic for reducing the stock of products
	// which have been bought in the current order
	@Override
	public Order confirmOrder(Order order) {
		
		Map<Integer, Integer> cart = order.getCart();
		int productQuantity = 0;
		Product product = null;
		
		if (cart == null)
			return order;
		
		for (Integer productId : cart.keySet()) {
			
			// Get the product
			product = productService.getProduct(productId);
			
			// Get the quantity of the current product 
			productQuantity = cart.get(productId);
			
			// Subtract the current product stock from the quantity bought and set the new stock
			product.setStock(product.getStock() - productQuantity);
			
			// Update the product in the database
			productService.updateProduct(product);
		}
		return order;
	}
}