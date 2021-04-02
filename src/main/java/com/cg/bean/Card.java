package com.cg.bean;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@SequenceGenerator(name = "cardSequence", initialValue = 501, allocationSize = 1)

@Entity
@Table(name = "System_card")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardSequence")
	private long id;
	private String name;
	@Column(name = "card_number")
	private String number;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate expiry;
    private int cvv;
	
    public Card() {
    	super();
    }
    
	public Card(String name, String number, LocalDate expiry, int cvv) {
		super();
		this.name = name;
		this.number = number;
		this.expiry = expiry;
		this.cvv = cvv;
	}

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
		return "Card [id=" + id + ", name=" + name + ", number=" + number + ", expiry=" + expiry + ", cvv=" + cvv + "]";
	}	
}
