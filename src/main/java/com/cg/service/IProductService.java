package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.bean.Product;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.ProductNotFoundException;


@Service
public interface IProductService {
	public Product addProduct(Product product) throws IncorrectPriceException;
	public String removeProduct(long id) throws ProductNotFoundException;
	public Product updateProduct(long id, Product product) throws ProductNotFoundException, IncorrectPriceException;
	public Product getProductById(long id) throws ProductNotFoundException;
	public List<Product> getAllProduct() throws EmptyInventoryException;
	public List<Optional<Product>> getProductsByName(String name) throws ProductNotFoundException;
	public List<Optional<Product>> getProductsBySize(String size) throws ProductNotFoundException;
	public List<Optional<Product>> getProductsByMrp(double mrp) throws ProductNotFoundException;
	public List<Optional<Product>> getProductsByColor(String color) throws ProductNotFoundException;
	
}
