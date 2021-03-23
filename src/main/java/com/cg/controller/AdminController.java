package com.cg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.bean.Admin;
import com.cg.bean.Customer;
import com.cg.bean.Product;
import com.cg.bean.User;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.ProductNotFoundException;
import com.cg.exception.UserNotFoundException;
import com.cg.service.ICustomerService;
import com.cg.service.IProductService;
import com.cg.service.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminController
{
	@Autowired
	IUserService userService;
	@Autowired
	ICustomerService customerService;
	@Autowired
	IProductService productService;
	
	
	// Admin signIn
	@PostMapping("/signIn")
	public ResponseEntity<User> signIn(@RequestBody User user) throws UserNotFoundException
	{
		userService.signIn(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// Admin signOut
	@PostMapping("/signOut")
	public ResponseEntity<User> signOut(@RequestBody User user)
	{
		userService.signOut(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//Add admin 
	@PostMapping("/register")
	public ResponseEntity<Admin> addUser(@RequestBody Admin admin) 
	{	
		userService.addUser(admin);
		return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
	}
	
	//Remove admin 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> removeUser(@PathVariable long id) throws UserNotFoundException {
		return new ResponseEntity<>(userService.removeUser(id), HttpStatus.OK);	
	}
	
	//Get all Users
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() 
	{	
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// Get all customers
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers() 
	{	
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}	

	// Get a single customer by id
	@GetMapping("/customers/{id}") 
	public ResponseEntity<Customer> getCustomer(@PathVariable long id) throws CustomerNotFoundException
	{
		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
	}

	@PostMapping("/product/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws IncorrectPriceException{
		productService.addProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	@GetMapping("/product/getall")
	public ResponseEntity<List<Product>>getAllProd() throws EmptyInventoryException{
		List <Product>productList= productService.getAllProduct();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK); 
		}

	@GetMapping("/product/getbyid/{id}")
	public ResponseEntity<Product>getProductById(@PathVariable long id) throws ProductNotFoundException{
		Product product= productService.getProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK); 
		}
	
	//Not Working
	@DeleteMapping("/product/delete/{id}") 
	public String deleteProduct(@PathVariable long id) throws ProductNotFoundException{  
		return productService.removeProduct(id); 
	}	

	@PutMapping("/product/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ProductNotFoundException, IncorrectPriceException{
		productService.updateProduct(product.getId(), product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/product/getbyname/{name}")
	public ResponseEntity<List<Optional<Product>>>getByName(@PathVariable String name) throws ProductNotFoundException{
		List<Optional<Product>> product = productService.getProductsByName(name);
		return new ResponseEntity<List<Optional<Product>>>(product, HttpStatus.OK);
	}
	
	@GetMapping("/product/getbysize/{size}")
	public ResponseEntity<List<Optional<Product>>>getBySize(@PathVariable String size) throws ProductNotFoundException{
		List<Optional<Product>> product = productService.getProductsBySize(size);
		return new ResponseEntity<List<Optional<Product>>>(product, HttpStatus.OK);
	}

	@GetMapping("/product/getbyprice/{mrp}")
	public ResponseEntity<List<Optional<Product>>>getByPrice(@PathVariable double mrp) throws ProductNotFoundException{
		List<Optional<Product>> product = productService.getProductsByMrp(mrp);
		return new ResponseEntity<List<Optional<Product>>>(product, HttpStatus.OK);
	}
	
	@GetMapping("/product/getbycolor/{color}")
	public ResponseEntity<List<Optional<Product>>>getByColor(@PathVariable String color) throws ProductNotFoundException{
		List<Optional<Product>> product = productService.getProductsByColor(color);
		return new ResponseEntity<List<Optional<Product>>>(product, HttpStatus.OK);
	}
	
}
