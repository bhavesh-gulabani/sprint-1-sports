package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.bean.Product;
import com.cg.exception.ProductNotFoundException;


@Service
public interface IProductService {
	public Product addProduct(Product product);
	public Product removeProduct(long id) throws ProductNotFoundException;
	public Product updateProduct(long id, Product product);
	public Product getProduct(long id) throws ProductNotFoundException;
	public List<Product> getAllProduct();
	public List<Optional<Product>> getProductsByName(String name);
	public List<Optional<Product>> getProductsBySize(String size);
	public List<Optional<Product>> getProductsByMrp(double mrp);
	public List<Optional<Product>> getProductsByColor(String color);
}
