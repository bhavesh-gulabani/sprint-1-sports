package com.cg.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "System_payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String type;
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Card card;
	
	// Constructors
	public Payment() {
		super();
	}
	
	public Payment(String type, String status, Card card) {
		super();
		this.type = type;
		this.status = status;
		this.card = card;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	// toString
	@Override
	public String toString() {
		return "Payment [id=" + id + ", type=" + type + ", status=" + status + ", card=" + card + "]";
	}
	
}
