package com.cg.service; 

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bean.Product;
import com.cg.dao.IProductRepository;
import com.cg.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
    private IProductRepository repo; 

	@Override
	public Product addProduct(Product product) {
		return repo.save(product);
	}

	@Override
	public Product removeProduct(long id) throws ProductNotFoundException{
		repo.deleteById(id);
		return repo.getOne(id);
	}

	@Override
	public Product updateProduct(long id, Product product) {
		return repo.save(product);
	}

	@Override
	public Product getProduct(long id) throws ProductNotFoundException {
		return repo.save(repo.getOne(id));
	}

	@Override
	public List<Product> getAllProduct() {
		return repo.findAll();
	}

	@Override
	public List<Optional<Product>> getProductsByName(String name)  {
		return repo.findAllByName(name);
	}

	@Override
	public List<Optional<Product>> getProductsBySize(String size) {
		return repo.findBySize(size);
	}

	@Override
	public List<Optional<Product>> getProductsByMrp(double mrp) {
		return repo.findByMrp(mrp);
	}

	@Override
	public List<Optional<Product>> getProductsByColor(String color) {
		return repo.findByColor(color);
	}



}