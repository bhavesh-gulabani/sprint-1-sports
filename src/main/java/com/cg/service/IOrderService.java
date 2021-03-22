package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bean.Order;
import com.cg.exception.OrderNotFoundException;

@Service
public interface IOrderService {
	public Order addOrder(Order order);
	public Order removeOrder(long id) throws OrderNotFoundException;
	public Order updateOrder(Order order) throws OrderNotFoundException;
	public Order updateOrder(long id) throws OrderNotFoundException;
	public Order getOrderDetails(long id) throws OrderNotFoundException;
	public List<Order> getAllOrders();
	public Order computeTotalAmount(Order order);
	public Order confirmOrder(Order order);
}