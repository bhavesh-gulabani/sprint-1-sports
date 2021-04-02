package com.cg.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@SequenceGenerator(name = "paymentSequence", initialValue = 401, allocationSize = 1)

@Entity
@Table(name = "System_payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentSequence")
	private long id;
	private String type;
	private String status;
	
	// Bidirectional one-to-one (Inverse side)
	@OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "paymentReference")
	private Order order;
	
	// Unidirectional one-to-one(Owning side)
	@OneToOne(cascade = CascadeType.ALL)
	private Card card;
	
	public Payment() {
		super();
	}
	
	public Payment(String type, String status, Order order, Card card) {
		super();
		this.type = type;
		this.status = status;
		this.order = order;
		this.card = card;
	}
	
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", type=" + type + ", status=" + status + ", order=" + order + ", card=" + card
				+ "]";
	}
}
