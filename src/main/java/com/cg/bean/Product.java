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

//For generating values of product id
@SequenceGenerator(name = "productSequence", initialValue = 201, allocationSize = 1)

@Entity
@Table(name = "System_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequence")
	private long id;
	private String name;
	private String category;
	private String description;
	private String brand; 
	private String color;
	@Column(name = "product_size")
	private String size;
	private double mrp;
	private int discount;
	private double priceAfterDiscount;
	private int stock;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate estimatedDelivery;
	
	// Constructors
	public Product() {
		super();
	}
	
	public Product(String name, String category, String description, String brand, String color, String size,
			double mrp, int discount, int stock, LocalDate estimatedDelivery) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.brand = brand;
		this.color = color;
		this.size = size;
		this.mrp = mrp;
		this.discount = discount;
		this.priceAfterDiscount = mrp - ((double)discount * (mrp / 100));
		this.stock = stock;
		this.estimatedDelivery = estimatedDelivery;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getPriceAfterDiscount() {
		priceAfterDiscount = mrp - ((double)discount * (mrp / 100));
		return priceAfterDiscount;
	}

	public void setPriceAfterDiscount(double priceAfterDiscount) {
		this.priceAfterDiscount = priceAfterDiscount;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public LocalDate getEstimatedDelivery() {
		return estimatedDelivery;
	}

	public void setEstimatedDelivery(LocalDate estimatedDelivery) {
		this.estimatedDelivery = estimatedDelivery;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", description=" + description
				+ ", brand=" + brand + ", color=" + color + ", size=" + size + ", mrp=" + mrp + ", discount=" + discount
				+ ", priceAfterDiscount=" + priceAfterDiscount + ", stock=" + stock + ", estimatedDelivery="
				+ estimatedDelivery + "]";
	}
}