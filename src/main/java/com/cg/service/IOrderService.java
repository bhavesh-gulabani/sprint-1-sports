package com.cg.service;

import java.util.List;

import com.cg.bean.Order;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.NotEnoughStockException;
import com.cg.exception.ResourceNotFoundException;

public interface IOrderService {
	public Order addOrder(Order order, long customerId) throws ResourceNotFoundException ;
	public Order removeOrder(long id) throws ResourceNotFoundException;
	public Order updateOrder(Order order, long customerId) throws ResourceNotFoundException;
	public Order updateOrder(long id) throws ResourceNotFoundException;
	public Order getOrderDetails(long id) throws ResourceNotFoundException;
	public List<Order> getAllOrders();
	
	public Order computeTotalAmount(Order order);
	public Order completeOrder(long orderId, long customerId, long paymentId) throws ResourceNotFoundException, IncorrectPriceException, NotEnoughStockException;
	Order checkProductStock(long id) throws NotEnoughStockException, ResourceNotFoundException;
}
