package com.cg.bean;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cg.util.CustomDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SequenceGenerator(name = "cartSequence", initialValue = 601, allocationSize = 1)

@Entity
@Table(name = "Test_System_cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartSequence")
	private long id;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "Test_System_items")
	@Column(name = "quantity")
	@JsonDeserialize(keyUsing = CustomDeserializer.class)
	private Map<Product, Integer> items = new HashMap<>();
	private double totalAmount;
	
	public Cart() {
		
	}
	
	public Cart(long id, Map<Product, Integer> items, double totalAmount) {
		super();
		this.id = id;
		this.items = items;
		this.totalAmount = totalAmount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<Product, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Product, Integer> items) {
		this.items = items;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", items=" + items + ", totalAmount=" + totalAmount + "]";
	}

}
