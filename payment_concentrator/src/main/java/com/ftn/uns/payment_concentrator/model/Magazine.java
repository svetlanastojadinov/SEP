package com.ftn.uns.payment_concentrator.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Magazine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String title;

	@Id
	@Column(nullable = false, unique = true)
	private String issn;

	@Column
	@Enumerated(EnumType.STRING)
	private Subscription subscription;
	
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

	@Column
	private String merchantId;

	@Column
	private double price;

	public Magazine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Magazine(String title, String issn, Subscription subscription, Collection<Article> articles) {
		super();
		this.title = title;
		this.issn = issn;
		this.subscription = subscription;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantUsername) {
		this.merchantId = merchantUsername;
	}

	@Override
	public String toString() {
		return issn + " " + title + " " + subscription + " " + price;
	}
<<<<<<< HEAD

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
=======
>>>>>>> e45c5b63e7a931d2990da712f7e3859e860d059c

}
