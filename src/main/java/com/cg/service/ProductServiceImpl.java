package com.cg.service; 

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bean.Product;
import com.cg.dao.IProductRepository;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
    private IProductRepository prodRepo; 

	@Override
	public Product addProduct(Product product) throws IncorrectPriceException {
		if(product.getMrp()<=0) 
			throw new IncorrectPriceException("Please enter correct price");
		else return prodRepo.save(product);
	}

	@Override
	public String removeProduct(long id) throws ProductNotFoundException{
		if(prodRepo.findById(id).isEmpty())
		  throw new ProductNotFoundException("No product found, check the id!");
		else {
			String msg = prodRepo.findById(id).get().getName() + " deleted from the inventory";
			prodRepo.deleteById(id);
			return msg;
		}		  
	}

	@Override
	public Product updateProduct(long id, Product product) throws ProductNotFoundException, IncorrectPriceException {
		if(prodRepo.findById(id).isEmpty())
			throw new ProductNotFoundException("No product found, check the id!");
		else if(product.getMrp()<=0)
			throw new IncorrectPriceException("Please enter correct price");
		else return prodRepo.save(product);
	}

	@Override
	public Product getProductById(long id) throws ProductNotFoundException {
		if(prodRepo.findById(id).isEmpty())
			throw new ProductNotFoundException("No product found, check the id!");
		else return prodRepo.findById(id).get();
	}

	@Override
	public List<Product> getAllProducts() throws EmptyInventoryException{
		if(prodRepo.findAll().isEmpty())
			throw new EmptyInventoryException("The Inventory is Empty!Please add some products first...!");
		return prodRepo.findAll();
	}

	@Override
	public List<Product> getProductsByName(String name) throws ProductNotFoundException {
		if(prodRepo.findAllByName(name).isEmpty())  
			 throw new ProductNotFoundException("No such Product !!");
		else return prodRepo.findAllByName(name);
	}


	
	@Override
	public List<Product> getProductsBySize(String size)  throws ProductNotFoundException {
		if(prodRepo.findAllBySize(size).isEmpty()) 
			 throw new ProductNotFoundException("Sorry, given size is not available!");
	    else return prodRepo.findAllBySize(size);
	}

	@Override
	public List<Product> getProductsByMrp(double mrp) throws ProductNotFoundException {
		if(prodRepo.findAllByMrp(mrp).isEmpty())
			throw new ProductNotFoundException("Nothing for the given price, check back later!");
		return prodRepo.findAllByMrp(mrp);
	}

	@Override
	public List<Product> getProductsByColor(String color) throws ProductNotFoundException {
		if(prodRepo.findAllByColor(color).isEmpty())
			throw new ProductNotFoundException("No product of " +color +" color");
		return prodRepo.findAllByColor(color);
	}


}