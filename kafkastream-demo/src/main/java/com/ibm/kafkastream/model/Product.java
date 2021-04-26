package com.ibm.kafkastream.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;

import java.sql.Timestamp;



@Entity
@Table(name="zenith_om_product")
public class Product {

	
	@Id
	@GeneratedValue
	@Column(name="`id`")
	private Long id;
	
	@Column(name="`name`")
	private String name;
	
	@Column(name="`quantity`")
	private Long quantity;
	
	@Column(name="`price`")
	private float price;
	
	@Transient
	private Timestamp  current_timestamp;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Timestamp getCurrent_timestamp()
	
	{
	 Date date= new Date();
     //getTime() returns current time in milliseconds
 long time = date.getTime();
     //Passed the milliseconds to constructor of Timestamp class 
 Timestamp ts = new Timestamp(time);
 System.out.println("Current Time Stamp: "+ts);
 
 return ts;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Product [Id=" + id + ", name=" + name + ", quantity=" + quantity + ", price="
				+ price +"]";
	}
				
}
