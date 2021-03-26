package com.cg.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "System_admin")
public class Admin extends User {
	@NotBlank(message = "Name cannot be blank")
	private String name;
	@Email(message = "Invalid email")
	private String email;
	private String contactNo;
	
	public Admin() {
		super();
	}

	public Admin(String username, String password, String role, String name, String email, String contactNo) {
		super(username, password, role);
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
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

	@Override
	public String toString() {
		return "Admin [name=" + name + ", email=" + email + ", contactNo=" + contactNo + "]";
	}
	
}
