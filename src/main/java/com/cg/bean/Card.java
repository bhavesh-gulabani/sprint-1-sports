package com.cg.bean;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "System_card")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String cardNumber;		// as Number is a keyword in Oracle, so named cardNumber
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate expiry;
    private int cvv;
	
    // Constructors
    public Card() {
    	super();
    }
    
    public Card(String name, String number, LocalDate expiry, int cvv) {
		super();
		this.name = name;
		this.cardNumber = number;
		this.expiry = expiry;
		this.cvv = cvv;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String number) {
		this.cardNumber = number;
	}

	public LocalDate getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDate expiry) {
		this.expiry = expiry;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + ", number=" + cardNumber + ", expiry=" + expiry + ", cvv=" + cvv + "]";
	}
	
}
