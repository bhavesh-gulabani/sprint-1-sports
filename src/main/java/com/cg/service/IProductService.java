package com.cg.service;

import java.util.List;

import com.cg.bean.Product;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.ResourceNotFoundException;

public interface IProductService {
	public Product addProduct(Product product) throws IncorrectPriceException;
	public String removeProduct(long id) throws ResourceNotFoundException;
	public Product updateProduct(long id, Product product) throws ResourceNotFoundException, IncorrectPriceException;
	public Product getProductById(long id) throws ResourceNotFoundException;
	public List<Product> getAllProducts() throws EmptyInventoryException;
	public List<Product> getProductsByName(String name) throws ResourceNotFoundException;
	public List<Product> getProductsBySize(String size) throws ResourceNotFoundException;
	public List<Product> getProductsByPrice(double price) throws ResourceNotFoundException;
	public List<Product> getProductsByColor(String color) throws ResourceNotFoundException;
	public List<Product> getProductsByCategory(String categoryName) throws ResourceNotFoundException;
}
