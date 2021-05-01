package com.cg.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Test_System_admin")
public class Admin extends User {
	@NotBlank(message = "Name cannot be blank")
	private String name;
	private String contactNo;
	private String imageUrl;
	
	public Admin() {
		super();
	}

	public Admin(@NotBlank(message = "Email cannot be blank") @Email String email,
			@NotBlank(message = "Password cannot be blank") String password, String role,
			@NotBlank(message = "Name cannot be blank") String name, String contactNo, String imageUrl) {
		super(email, password, role);
		this.name = name;
		this.contactNo = contactNo;
		this.imageUrl = imageUrl;
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
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Admin [name=" + name + ", contactNo=" + contactNo + ", imageUrl=" + imageUrl + "]";
	}
}
