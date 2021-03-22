package com.cg.bean;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

//For generating values of order id
@SequenceGenerator(name = "orderSequence", initialValue = 301, allocationSize = 1)

@Entity
@Table(name = "System_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
	private long id;
	private double amount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate billingDate;
	
	// Cart will contain the productId -> quantity for that product
	// Then based on that cart, we can calculate the total amount for the order
	@ElementCollection
	@CollectionTable(name = "System_Order_Cart")
	@MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
	Map<Integer, Integer> cart = new HashMap<>();
	
	// We can get product details from the productId which is the key of the map,
	// and we can perform operations using that product
	
	// Bidirectional many-to-one (Owning side)
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference(value = "customerReference")
	private Customer customer;
	
	// Bidirectional one-to-one (Owning side)
	@OneToOne
	@JoinColumn(name = "payment_id")
	@JsonBackReference(value = "paymentReference")
	private Payment payment;

	// Constructors
	public Order() {
		super();
	}
	
	public Order(double amount, LocalDate billingDate, Map<Integer, Integer> cart, Customer customer,
			Payment payment) {
		super();
		this.amount = amount;
		this.billingDate = billingDate;
		this.cart = cart;
		this.customer = customer;
		this.payment = payment;
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
	
	public Map<Integer, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<Integer, Integer> cart) {
		this.cart = cart;
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

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", amount=" + amount + ", billingDate=" + billingDate + ", cart=" + cart
				+ ", customer=" + customer + ", payment=" + payment + "]";
	}
}