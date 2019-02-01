package com.ftn.uns.payment_concentrator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Article implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String title;
<<<<<<< HEAD
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	

	private String author;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
=======

	@Column
	private String merchantId;

	@Column
	private double price;	
>>>>>>> e45c5b63e7a931d2990da712f7e3859e860d059c

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(long id, String title) {
		super();
		this.id = id;
		this.title = title;
<<<<<<< HEAD
=======
		this.merchantId = authorUsername;
>>>>>>> e45c5b63e7a931d2990da712f7e3859e860d059c
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

<<<<<<< HEAD
=======
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantUsername) {
		this.merchantId = merchantUsername;
	}
>>>>>>> e45c5b63e7a931d2990da712f7e3859e860d059c

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
<<<<<<< HEAD
		return id + " " + " " + title + " ";
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
=======
		return id + " " + " " + title + " " + merchantId + " " + price;
>>>>>>> e45c5b63e7a931d2990da712f7e3859e860d059c
	}

}
