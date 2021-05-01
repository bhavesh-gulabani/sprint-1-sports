package com.cg.bean;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;


@SequenceGenerator(name = "orderSequence", initialValue = 301, allocationSize = 1)

@Entity
@Table(name = "Test_System_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
	private long id;
	private double amount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate billingDate;
	
	// Bidirectional many-to-one (Owning side)
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference(value = "customerReference")
	private Customer customer;
	
	// Bidirectional one-to-one (Owning side)
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "payment_id")
	@JsonBackReference(value = "paymentReference")
	private Payment payment;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;

	public Order() {
		super();
	}
	
	public Order(long id, double amount, LocalDate billingDate, Customer customer, Payment payment, Cart cart) {
		super();
		this.id = id;
		this.amount = amount;
		this.billingDate = LocalDate.now();;
		this.customer = customer;
		this.payment = payment;
		this.cart = cart;
	}

	public Order(double amount, LocalDate billingDate, Customer customer, Payment payment, Cart cart) {
		super();
		this.amount = amount;
		this.billingDate = LocalDate.now();;
		this.customer = customer;
		this.payment = payment;
		this.cart = cart;
	}

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
		this.billingDate = LocalDate.now();
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = LocalDate.now();
//		this.billingDate = billingDate;
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

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", amount=" + amount + ", billingDate=" + billingDate + ", customer=" + customer
				+ ", payment=" + payment + ", cart=" + cart + "]";
	}
	
	
	
}
