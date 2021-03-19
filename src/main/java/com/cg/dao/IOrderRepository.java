package com.cg.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.bean.Order;

public interface IOrderRepository extends CrudRepository<Order, Long> {

}
