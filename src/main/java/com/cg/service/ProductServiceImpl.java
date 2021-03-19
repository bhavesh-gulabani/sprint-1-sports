package com.cg.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Product;
import com.cg.dao.IProductRepository;


@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
    private IProductRepository repo; 

	@Override
	public Product addProduct(Product product) {
		return repo.save(product);
	}

	@Override
	public Product removeProduct(long id) {
		
//		repo.deleteById(id);
		return null;
	}

	@Override
	public Product updateProduct(long id, Product product) {
		return repo.save(product);
	}

	@Override
	public Product getProduct(long id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Product> getAllProduct() {
		return (List<Product>) repo.findAll();
	}

	@Override
	public List<Product> getProductsByName() {
		return null;
	}

	@Override
	public List<Product> getProductsBySize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByColor() {
		// TODO Auto-generated method stub
		return null;
	}



}
