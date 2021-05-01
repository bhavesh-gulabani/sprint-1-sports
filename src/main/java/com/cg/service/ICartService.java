package com.cg.service;

import java.util.List;

import com.cg.bean.Cart;
import com.cg.exception.ResourceNotFoundException;

public interface ICartService {
	public Cart addCart(Cart cart) throws ResourceNotFoundException ;
	public Cart removeCart(long id) throws ResourceNotFoundException;
	public Cart updateCart(Cart cart) throws ResourceNotFoundException;
	public Cart getCart(long id) throws ResourceNotFoundException;
	public List<Cart> getAllCarts() throws ResourceNotFoundException; // not necessary
	
	public Cart computeTotalAmount(Cart cart);
	
}
