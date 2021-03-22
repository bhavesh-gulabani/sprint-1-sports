package com.cg.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> { 
	 List<Product> findAllByName(String name);
	 List<Product> findAllBySize(String size);
	 List<Product> findAllByMrp(double mrp);
	 List<Product> findAllByColor(String color);
	 List<Product> findAllByCategory(String categoryName);
}