package com.cg.service;

import java.util.List;

import com.cg.bean.Product;

public interface IProductService {
	public Product addProduct(Product product);
	public Product removeProduct(long id);
	public Product updateProduct(Product product);
	public Product getProduct(long id);
	public List<Product> getAllProduct();
	public List<Product> getProductsByName(String name);
	public List<Product> getProductsBySize(String size);
	public List<Product> getProductsByPrice(double price);
	public List<Product> getProductsByColor(String color);
}
