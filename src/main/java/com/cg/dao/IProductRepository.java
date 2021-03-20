package com.cg.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> { 
	 List<Optional<Product>> findAllByName(String name);
	 List<Optional<Product>> findBySize(String size);
	 List<Optional<Product>> findByMrp(double mrp);
	 List<Optional<Product>> findByColor(String color);
}