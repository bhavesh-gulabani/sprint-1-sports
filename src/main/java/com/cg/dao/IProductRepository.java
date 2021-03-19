package com.cg.dao;

import org.springframework.data.repository.CrudRepository;

import com.cg.bean.Product;

public interface IProductRepository extends CrudRepository<Product, Long>{
}
