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
	
	public Order addOrder(Order order) {
		computeTotalAmount(order);
		return orderRepository.save(order);
	}

	public Order removeOrder(long id) throws ResourceNotFoundException {
		Order orderToBeRemoved = getOrderDetails(id);
		orderRepository.deleteById(id);
		return orderToBeRemoved;
	}

	public Order updateOrder(Order order) throws ResourceNotFoundException {
		getOrderDetails(order.getId());
		computeTotalAmount(order);
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
		return updateOrder(order);
	}
	
	// Method that handles business logic for calculating the total order amount
	@Override
	public Order computeTotalAmount(Order order) {
		Map<Product, Integer> cart = order.getCart();
		double totalAmount = 0;
		
		if (cart == null) {
			order.setAmount(totalAmount);
			return order;
		}
			
		for (Product product : cart.keySet())
			totalAmount += product.getPriceAfterDiscount() * cart.get(product);

		order.setAmount(totalAmount);
		return order;
	}
	
	// Method that handles business logic for reducing the stock of products
	// which have been bought in the current order
	@Override
	public Order confirmOrder(Order order) throws ResourceNotFoundException, IncorrectPriceException, NotEnoughStockException {
		Map<Product, Integer> cart = order.getCart();
		int productQuantity = 0;
		
		if (cart == null)
			return order;
		
		for (Product product : cart.keySet()) {
			 
			productQuantity = cart.get(product);
			
			if (product.getStock() - productQuantity < 0)
				throw new NotEnoughStockException("Not enough " + product.getName() + " products in the system");
			
			product.setStock(product.getStock() - productQuantity);	
			productService.updateProduct(product.getId(), product);
		}
		return order;
	}
}