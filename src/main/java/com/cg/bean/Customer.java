package com.cg.bean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Test_System_customer")
public class Customer extends User {
	@NotBlank
	private String name;
	private String contactNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private  LocalDate dateOfBirth;
	private String imageUrl;
	@Embedded
	private Address address;
	
	private String status;
	
	// Bidirectional one-to-many (Inverse side)
	@OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JsonManagedReference(value = "customerReference")
	private Set<Order> orders = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
	public Customer() {
		super();
	}
	
	public Customer(@NotBlank(message = "Email cannot be blank") @Email String email,
			@NotBlank(message = "Password cannot be blank") String password, String role, @NotBlank String name,
			String contactNo, LocalDate dateOfBirth, String imageUrl, Address address, String status, Set<Order> orders,
			Cart cart) {
		super(email, password, role);
		this.name = name;
		this.contactNo = contactNo;
		this.dateOfBirth = dateOfBirth;
		this.imageUrl = imageUrl;
		this.address = address;
		this.status = status;
		this.orders = orders;
		this.cart = cart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
	    orders.add(order);
	    order.setCustomer(this);
	}

	public void removeOrder(Order order) {
	    orders.remove(order);
	    order.setCustomer(null);
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", contactNo=" + contactNo + ", dateOfBirth=" + dateOfBirth + ", imageUrl="
				+ imageUrl + ", address=" + address + ", status=" + status + ", orders=" + orders + ", cart=" + cart
				+ "]";
	}

	
}