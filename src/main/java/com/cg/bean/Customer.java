package com.cg.bean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "System_customer")
public class Customer extends User {
	
	private String name;
	private String email;
	private String contactNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private  LocalDate dateOfBirth;
	
	@Embedded
	private Address address;
	
	// Bi-directional one-to-many (Inverse side)
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	private Set<Order> orders = new HashSet<>();
	
	// Constructors
	public Customer() {
		super();
	}
	
	public Customer(String name, String email, String contactNo, LocalDate dateOfBirth, Address address,
			Set<Order> orders) {
		super();
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.orders = orders;
	}

	public Customer(String username, String password, String role, String name, String email) {
		super(username, password, role);
		this.name = name;
		this.email = email;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setDob(LocalDate dateOfBirth) {
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

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", contactNo=" + contactNo + ", dateOfBirth="
				+ dateOfBirth + ", address=" + address + ", orders=" + orders + "]";
	}

}