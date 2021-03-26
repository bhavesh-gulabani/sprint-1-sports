package com.cg.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Product;
import com.cg.dao.IProductRepository;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.ResourceNotFoundException;


@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	
	@Autowired
    private IProductRepository productRepository; 
	
	@Override
	public Product addProduct(Product product) throws IncorrectPriceException {
		if(product.getMrp() <= 0) 
			throw new IncorrectPriceException("Please enter correct price");
		else return productRepository.save(product);
	}

	@Override
	public String removeProduct(long id) throws ResourceNotFoundException {
		if(productRepository.findById(id).isEmpty())
			  throw new ResourceNotFoundException("No product found, check the id!");
		else {
			String msg = productRepository.findById(id).get().getName() + " deleted from the inventory";
			productRepository.deleteById(id);
			return msg;
		}
	}

	@Override
	public Product updateProduct(long id, Product product) throws ResourceNotFoundException, IncorrectPriceException {
		if(productRepository.findById(id).isEmpty())
			throw new ResourceNotFoundException("No product found, check the id!");
		else if(product.getMrp() <= 0)
			throw new IncorrectPriceException("Please enter correct price");
		else return productRepository.save(product);
	}

	@Override
	public Product getProductById(long id) throws ResourceNotFoundException {
		if(productRepository.findById(id).isEmpty())
			throw new ResourceNotFoundException("No product found, check the id!");
		else return productRepository.findById(id).get();
	}

	@Override
	public List<Product> getAllProducts() throws EmptyInventoryException {
		if(productRepository.findAll().isEmpty())
			throw new EmptyInventoryException("The Inventory is Empty! Please add some products first...!");
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByName(String name) throws ResourceNotFoundException {
		if(productRepository.findAllByName(name).isEmpty())  
			 throw new ResourceNotFoundException("No such Product !!");
		else return productRepository.findAllByName(name);
	}

	@Override
	public List<Product> getProductsBySize(String size) throws ResourceNotFoundException {
		if(productRepository.findAllBySize(size).isEmpty()) 
			 throw new ResourceNotFoundException("Sorry, given size is not available!");
	    else return productRepository.findAllBySize(size);
	}

	@Override
	public List<Product> getProductsByPrice(double price) throws ResourceNotFoundException {
		if(productRepository.findAllByPriceAfterDiscount(price).isEmpty())
			throw new ResourceNotFoundException("Nothing for the given price, check back later!");
		return productRepository.findAllByPriceAfterDiscount(price);
	}

	@Override
	public List<Product> getProductsByColor(String color) throws ResourceNotFoundException {
		if(productRepository.findAllByColor(color).isEmpty())
			throw new ResourceNotFoundException("No product of " +color +" color");
		return productRepository.findAllByColor(color);
	}


	@Override
	public List<Product> getProductsByCategory(String categoryName) throws ResourceNotFoundException {
		if (productRepository.findAllByCategory(categoryName).isEmpty())
			throw new ResourceNotFoundException("Product category does not exist");
		return productRepository.findAllByCategory(categoryName);		
	}
}