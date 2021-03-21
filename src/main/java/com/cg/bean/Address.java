package com.cg.bean;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Address {

	private String doorNo;
	@Size(min = 5, max = 20, message = "Street should be between 5 to 30 characters")
	private String street;
	@Size(min = 5, max = 15, message = "Area should be between 5 to 15 characters")
	private String area;
	private String city;
	private String state;
	private int pincode;
	
	// Constructors
	public Address() {
		super();
	}

	public Address(String doorNo,
			@Size(min = 5, max = 20, message = "Street should be between 5 to 30 characters") String street,
			@Size(min = 5, max = 15, message = "Area should be between 5 to 15 characters") String area, String city,
			String state, int pincode) {
		super();
		this.doorNo = doorNo;
		this.street = street;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	// Getters and Setters
	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public int getPincode() {
		return pincode;
	}
	
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Address [doorNo=" + doorNo + ", street=" + street + ", area=" + area + ", city=" + city + ", state="
				+ state + ", pincode=" + pincode + "]";
	}
}