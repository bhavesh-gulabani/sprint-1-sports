package com.cg.dao;

import org.springframework.data.repository.CrudRepository;
import com.cg.bean.Product;

public interface IProductRepository extends CrudRepository<Product, Long>{
	Iterable<Product> findAllByName(String name);
	Iterable<Product> findAllBySize(String size);
	Iterable<Product> findAllByPriceAfterDiscount(double price);
	Iterable<Product> findAllByColor(String color);
}