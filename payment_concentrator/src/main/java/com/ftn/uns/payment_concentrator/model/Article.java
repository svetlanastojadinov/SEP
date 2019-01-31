package com.ftn.uns.payment_concentrator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {

	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String title;

	@Column
	private String merchantId;

	@Column
	private double price;	

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(long id, String title, String authorUsername) {
		super();
		this.id = id;
		this.title = title;
		this.merchantId = authorUsername;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantUsername) {
		this.merchantId = merchantUsername;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return id + " " + " " + title + " " + merchantId + " " + price;
	}

}
