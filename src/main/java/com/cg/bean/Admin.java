package com.cg.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "System_admin")
public class Admin extends User {
	
	private String name;
	private String email;
	private String contactNo;
	
	//Constructors
	public Admin() {
		super();
	}

	public Admin(String username, String password, String role, String name, String email, String contactNo) {
		super(username, password, role);
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
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

	//toString
	@Override
	public String toString() {
		return "Admin [name=" + name + ", email=" + email + ", contactNo=" + contactNo + "]";
	}
	
}
