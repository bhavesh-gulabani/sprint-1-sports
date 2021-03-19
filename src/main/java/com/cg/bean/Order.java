// Best approach : 
//	In one order, how many products are ordered?
//	Map<Integer, Product> ProductId -> ProductObject
//							(key)		(value)
// quantity as a single variable for every product
// Inside product class, quantity will be a field


//	@CollectionTable(name="Product_Quantity", joinColumns=@JoinColumn(name="product_qty"))
//	@Column(name="student_name")
package com.cg.bean;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "System_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private double amount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate billingDate;
	
	// Cart will contain the Product -> Quantity for that product
	// Then based on that cart, we can calculate the total amount for the order
	@CollectionTable(name="System_Order_Cart")
	@ElementCollection
	Map<Product, Integer> cart;
	
	// Bi-directional many-to-one (Owning side)
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	// Bi-directional One-to-One (Owning side)
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Payment payment;

	// Constructors
	public Order() {
		super();
		cart = new HashMap<>();
	}
	
	public Order(double amount, LocalDate billingDate, Map<Product, Integer> cart, Customer customer) {
		super();
		this.amount = amount;
		this.billingDate = billingDate;
		this.cart = cart;
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



	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", amount=" + amount + ", billingDate=" + billingDate + ", cart=" + cart
				+ ", customer=" + customer + "]";
	}

}
