package com.cg.service; 

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bean.Product;
import com.cg.dao.IProductRepository;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
    private IProductRepository repo; 

	@Override
	public Product addProduct(Product product) throws IncorrectPriceException {
		if(product.getMrp()<=0) 
			throw new IncorrectPriceException("Please enter correct price");
		else return repo.save(product);
	}

	@Override
	public String removeProduct(long id) throws ProductNotFoundException{
		if(repo.findById(id).isEmpty())
		  throw new ProductNotFoundException("No product found, check the id!");
		else {
			String msg = repo.getOne(id).getName() + " deleted from the inventory";
			repo.deleteById(id);
			return msg;
		}		  
	}

	@Override
	public Product updateProduct(long id, Product product) throws ProductNotFoundException, IncorrectPriceException {
		if(repo.findById(id).isEmpty())
			throw new ProductNotFoundException("No product found, check the id!");
		else if(product.getMrp()<=0)
			throw new IncorrectPriceException("Please enter correct price");
		else return repo.save(product);
	}

	@Override
	public Product getProductById(long id) throws ProductNotFoundException {
		if(repo.findById(id).isEmpty())
			throw new ProductNotFoundException("No product found, check the id!");
		else return repo.getOne(id);
	}

	@Override
	public List<Product> getAllProduct() throws EmptyInventoryException{
		if(repo.findAll().isEmpty())
			throw new EmptyInventoryException("The Inventory is Empty!Please add some products first...!");
		return repo.findAll();
	}

	@Override
	public List<Optional<Product>> getProductsByName(String name) throws ProductNotFoundException {
		if(repo.findAllByName(name).isEmpty())  
			 throw new ProductNotFoundException("No such Product !!");
		else return repo.findAllByName(name);
	}


	
	@Override
	public List<Optional<Product>> getProductsBySize(String size)  throws ProductNotFoundException {
		if(repo.findBySize(size).isEmpty()) 
			 throw new ProductNotFoundException("Sorry, given size is not available!");
	    else return repo.findBySize(size);
	}

	@Override
	public List<Optional<Product>> getProductsByMrp(double mrp) throws ProductNotFoundException {
		if(repo.findByMrp(mrp).isEmpty())
			throw new ProductNotFoundException("Nothing for the given price, check back later!");
		return repo.findByMrp(mrp);
	}

	@Override
	public List<Optional<Product>> getProductsByColor(String color) throws ProductNotFoundException {
		if(repo.findByColor(color).isEmpty())
			throw new ProductNotFoundException("No product of " +color +" color");
		return repo.findByColor(color);
	}


}