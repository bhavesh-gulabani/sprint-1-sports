package com.cg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Product;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.ProductNotFoundException;
import com.cg.service.IProductService;

@RestController
@RequestMapping("/admin/product")
public class ProductController  {

@Autowired
private IProductService productService;

@PostMapping("/add")
public ResponseEntity<Product> addProduct(@RequestBody Product product) throws IncorrectPriceException{
	productService.addProduct(product);
	return new ResponseEntity<Product>(product, HttpStatus.CREATED);
}

@GetMapping("/getall")
public ResponseEntity<List<Product>>getAllProd() throws EmptyInventoryException{
	List <Product>productList= productService.getAllProducts();
	return new ResponseEntity<List<Product>>(productList, HttpStatus.OK); 
	}

@GetMapping("/getbyid/{id}")
public ResponseEntity<Product>getProductById(@PathVariable long id) throws ProductNotFoundException{
	Product product= productService.getProductById(id);
	return new ResponseEntity<Product>(product, HttpStatus.OK); 
	}

@DeleteMapping("/delete/{id}") 
public String deleteProduct(@PathVariable long id) throws ProductNotFoundException{  
	return productService.removeProduct(id); 
}	
 
@PutMapping("/update")
public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ProductNotFoundException, IncorrectPriceException{
	productService.updateProduct(product.getId(), product);
	return new ResponseEntity<Product>(product, HttpStatus.OK);
}

@GetMapping("/getbyname/{name}")
public ResponseEntity<List<Product>>getByName(@PathVariable String name) throws ProductNotFoundException{
	List<Product> product = productService.getProductsByName(name);
	return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
}

@GetMapping("/getbysize/{size}")
public ResponseEntity<List<Product>>getBySize(@PathVariable String size) throws ProductNotFoundException{
	List<Product> product = productService.getProductsBySize(size);
	return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
}

@GetMapping("/getbyprice/{mrp}")
public ResponseEntity<List<Product>>getByPrice(@PathVariable double mrp) throws ProductNotFoundException{
	List<Product> product = productService.getProductsByMrp(mrp);
	return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
}
@GetMapping("/getbycolor/{color}")
public ResponseEntity<List<Product>>getByColor(@PathVariable String color) throws ProductNotFoundException{
	List<Product> product = productService.getProductsByColor(color);
	return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
}
}


