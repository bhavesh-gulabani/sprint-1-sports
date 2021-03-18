package com.cg.bean;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "System_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private double amount;
	private LocalDate billingDate;
	
	@ElementCollection
	Map<Product, Integer> cart;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Payment payment;
	
	@ManyToOne
	private Customer customer;

	// Constructors
	public Order() {
		super();
		cart = new HashMap<>();
	}
	
	public Order(double amount, LocalDate billingDate, Map<Product, Integer> cart, Payment payment, Customer customer) {
		super();
		this.amount = amount;
		this.billingDate = billingDate;
		this.cart = cart;
		this.payment = payment;
		this.customer = customer;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", amount=" + amount + ", billingDate=" + billingDate + ", payment=" + payment
				+ ", customer=" + customer + "]";
	}
}
