package com.cg.service;

import java.util.List;

import com.cg.bean.Order;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.NotEnoughStockException;
import com.cg.exception.ResourceNotFoundException;

public interface IOrderService {
	public Order addOrder(Order order) ;
	public Order removeOrder(long id) throws ResourceNotFoundException;
	public Order updateOrder(Order order) throws ResourceNotFoundException;
	public Order updateOrder(long id) throws ResourceNotFoundException;
	public Order getOrderDetails(long id) throws ResourceNotFoundException;
	public List<Order> getAllOrders() ;
	public Order computeTotalAmount(Order order);
	public Order confirmOrder(Order order) throws ResourceNotFoundException, IncorrectPriceException, NotEnoughStockException;
}
