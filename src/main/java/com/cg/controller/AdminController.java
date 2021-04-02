package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Admin;
import com.cg.bean.Customer;
import com.cg.bean.Product;
import com.cg.bean.User;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.IncorrectPriceException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.WrongCredentialsException;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.service.ICustomerService;
import com.cg.service.IProductService;
import com.cg.service.IUserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IUserService userService;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IProductService productService;
	
	// Administrator operations =>
	@PostMapping("/signin")
	public ResponseEntity<Object> signIn(@RequestBody User user) throws ResourceNotFoundException, WrongCredentialsException {
		userService.signIn(user);
		return new ResponseEntity<>(user.getUsername() + " successfully logged in", HttpStatus.OK);
	}

	@PostMapping("/signout")
	public ResponseEntity<Object> signOut(@RequestBody User user) {
		userService.signOut(user);
		return new ResponseEntity<>(user.getUsername() + " successfully logged out", HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Admin> addUser(@RequestBody Admin admin)  throws UserAlreadyExistsException, ResourceNotFoundException {	
		userService.addUser(admin);
		return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
	}
	 
	@DeleteMapping("/{id}")
	public ResponseEntity<User> removeUser(@PathVariable long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(userService.removeUser(id), HttpStatus.OK);	
	}
	 
	@PutMapping
	public ResponseEntity<User> updateUser(@Valid @RequestBody Admin admin) throws ResourceNotFoundException {
		return new ResponseEntity<>(userService.updateUser(admin), HttpStatus.OK); 	
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() throws ResourceNotFoundException {	
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	// Customer Management =>
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers() throws ResourceNotFoundException {	
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/customers/{id}") 
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) throws ResourceNotFoundException { 
		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
	}
	
	// Product Management =>
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws IncorrectPriceException {
		productService.addProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	@GetMapping("/products") 
	public ResponseEntity<List<Product>>getAllProd() throws EmptyInventoryException {
		return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK); 
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product>getProductById(@PathVariable long id) throws ResourceNotFoundException {
		return new ResponseEntity<Product>(productService.getProductById(id), HttpStatus.OK); 
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable long id) throws ResourceNotFoundException {  
		return new ResponseEntity<String>(productService.removeProduct(id), HttpStatus.OK); 
	}	

	@PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ResourceNotFoundException, IncorrectPriceException {
		productService.updateProduct(product.getId(), product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/products/names/{name}") 
	public ResponseEntity<List<Product>>getByName(@PathVariable String name) throws ResourceNotFoundException {
		return new ResponseEntity<>(productService.getProductsByName(name), HttpStatus.OK);
	}

	@GetMapping("/products/sizes/{size}")
	public ResponseEntity<List<Product>>getBySize(@PathVariable String size) throws ResourceNotFoundException {
		return new ResponseEntity<>(productService.getProductsBySize(size), HttpStatus.OK);
	}

	@GetMapping("/products/prices/{price}")
	public ResponseEntity<List<Product>>getByPrice(@PathVariable double price) throws ResourceNotFoundException {
		return new ResponseEntity<List<Product>>(productService.getProductsByPrice(price), HttpStatus.OK);
	}

	@GetMapping("/products/colors/{color}")
	public ResponseEntity<List<Product>>getByColor(@PathVariable String color) throws ResourceNotFoundException {
		return new ResponseEntity<List<Product>>(productService.getProductsByColor(color), HttpStatus.OK);
	}
}