package com.cg.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Cart;
import com.cg.bean.Product;
import com.cg.dao.ICartRepository;
import com.cg.exception.ResourceNotFoundException;

@Transactional	
@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	ICartRepository cartRepository;
	
	@Override
	public Cart addCart(Cart cart) throws ResourceNotFoundException {
		return cartRepository.save(cart);
	}

	@Override
	public Cart removeCart(long id) throws ResourceNotFoundException {
		Cart cartToBeRemoved = getCart(id);
		cartRepository.deleteById(id);
		return cartToBeRemoved;
	}

	@Override
	public Cart updateCart(Cart cart) throws ResourceNotFoundException {
		computeTotalAmount(cart);
		System.out.println(cart);
		return cartRepository.save(cart);
	}

	@Override
	public Cart getCart(long id) throws ResourceNotFoundException {
		Optional<Cart> cartOptional = cartRepository.findById(id);
		return cartOptional.orElseThrow(() -> new ResourceNotFoundException("Cart details not found"));
	}

	@Override
	public List<Cart> getAllCarts() throws ResourceNotFoundException {
		List<Cart> cartList = (List<Cart>) cartRepository.findAll();
		if (cartList.isEmpty())
			throw new ResourceNotFoundException("No Carts found in the system");
		return cartList;
	}

	@Override
	public Cart computeTotalAmount(Cart cart) {	
		if (cart == null) return null;
		Map<Product, Integer> items = cart.getItems();
		double totalAmount = 0;
		
		if (items == null) {
			cart.setTotalAmount(totalAmount);
			return cart;
		}
			
		for (Product product : items.keySet())
			totalAmount += product.getPriceAfterDiscount() * items.get(product);

		cart.setTotalAmount(totalAmount);
		return cart;
	}

}
