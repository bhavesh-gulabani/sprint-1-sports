package com.cg.bean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "System_customer")
public class Customer extends User {
	@NotBlank
	private String name;
	@Email(message = "Email should be valid")
	private String email;
	private String contactNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private  LocalDate dateOfBirth;
	@Embedded
	private Address address;
	
	// Bidirectional one-to-many (Inverse side)
	@OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JsonManagedReference(value = "customerReference")
	private Set<Order> orders = new HashSet<>();
	
	public Customer() {
		super();
	}
	
	public Customer(String username, String password, String role, @NotBlank String name,
			@Email(message = "Email should be valid") String email, String contactNo, LocalDate dateOfBirth,
			Address address, Set<Order> orders) {
		super(username, password, role);
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.orders = orders;
	}
	
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

	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", contactNo=" + contactNo + ", dateOfBirth="
				+ dateOfBirth + ", address=" + address + ", orders=" + orders + "]";
	}
}