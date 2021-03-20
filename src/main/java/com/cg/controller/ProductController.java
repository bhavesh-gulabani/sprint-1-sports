package com.cg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Product;
import com.cg.exception.ProductNotFoundException;
import com.cg.service.IProductService;

@RestController
@RequestMapping("/admin/product")
public class ProductController extends WebSecurityConfigurerAdapter {

@Autowired
private IProductService productService;
	
@Override
protected void configure(HttpSecurity http) throws Exception {
       super.configure(http);
       http.csrf().disable();
}

@PostMapping("/add")
public ResponseEntity<Product> addProduct(@RequestBody Product product){
	productService.addProduct(product);
	return new ResponseEntity<Product>(product, HttpStatus.CREATED);
}

@GetMapping("/getall")
public ResponseEntity<List<Product>>getAllProd(){
	List <Product>productList= productService.getAllProduct();
	return new ResponseEntity<List<Product>>(productList, HttpStatus.OK); 
	}

@DeleteMapping("/delete/{id}") 
public String deleteProduct(@PathVariable long id) throws ProductNotFoundException{ 
	productService.removeProduct(id); 
	return "Deleted by id"; 
}	
 
@PutMapping("/update")
public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ProductNotFoundException{
	productService.updateProduct(product.getId(), product);
	return new ResponseEntity<Product>(product, HttpStatus.OK);
}

@GetMapping("/getbyname/{name}")
public ResponseEntity<List<Optional<Product>>>getByName(@PathVariable String name){
	List<Optional<Product>> product = productService.getProductsByName(name);
	return new ResponseEntity<List<Optional<Product>>>(product, HttpStatus.OK);
}

@GetMapping("/getbysize/{size}")
public ResponseEntity<List<Optional<Product>>>getBySize(@PathVariable String size){
	List<Optional<Product>> product = productService.getProductsBySize(size);
	return new ResponseEntity<List<Optional<Product>>>(product, HttpStatus.OK);
}

@GetMapping("/getbyprice/{mrp}")
public ResponseEntity<List<Optional<Product>>>getByPrice(@PathVariable double mrp){
	List<Optional<Product>> product = productService.getProductsByMrp(mrp);
	return new ResponseEntity<List<Optional<Product>>>(product, HttpStatus.OK);
}
@GetMapping("/getbycolor/{color}")
public ResponseEntity<List<Optional<Product>>>getByColor(@PathVariable String color){
	List<Optional<Product>> product = productService.getProductsByColor(color);
	return new ResponseEntity<List<Optional<Product>>>(product, HttpStatus.OK);
}
}


