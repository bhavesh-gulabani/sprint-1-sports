package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Order;
import com.cg.dao.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository orderRepository;
	
	
	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}

	
	public Order removeOrder(long id) {
		Order remo = getOrderDetails(id);
		orderRepository.deleteById(id);
		return remo;
	}

	
	public Order updateOrder(long id, Order order) {
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
}