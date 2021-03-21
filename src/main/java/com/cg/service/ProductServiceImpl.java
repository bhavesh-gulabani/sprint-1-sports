package com.cg.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Product;
import com.cg.dao.IProductRepository;


@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	
	@Autowired
    private IProductRepository productRepository; 

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product removeProduct(long id) {
		Product productToBeRemoved = getProduct(id);
		productRepository.deleteById(id);
		return productToBeRemoved;
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProduct(long id) {
		Optional<Product> productOptional = productRepository.findById(id);
		return productOptional.isEmpty() ? null : productOptional.get();
	}

	@Override
	public List<Product> getAllProduct() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByName(String name) {
		List<Product> products = (List<Product>) productRepository.findAllByName(name);
		return products.isEmpty() ? null : products;
	}

	@Override
	public List<Product> getProductsBySize(String size) {
		List<Product> products = (List<Product>) productRepository.findAllBySize(size);
		return products.isEmpty() ? null : products;
	}

	@Override
	public List<Product> getProductsByPrice(double price) {
		List<Product> products = (List<Product>) productRepository.findAllByPriceAfterDiscount(price);
		return products.isEmpty() ? null : products;
	}

	@Override
	public List<Product> getProductsByColor(String color) {
		List<Product> products = (List<Product>) productRepository.findAllByColor(color);
		return products.isEmpty() ? null : products;
	}
}