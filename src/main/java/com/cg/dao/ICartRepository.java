package com.cg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.Cart;

@Repository
public interface ICartRepository extends CrudRepository<Cart, Long> {

}
