package com.cg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.Order;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {
	
}
