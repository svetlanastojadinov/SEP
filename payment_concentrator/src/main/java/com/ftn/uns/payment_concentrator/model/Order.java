package com.ftn.uns.payment_concentrator.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="order_table")
public class Order {
	
	@Id
	@Column
	private long id;
	
	@Column
	private PaymentType paymentType;
	
	@Column
	private Double price;
	
	@Column
	private Date date;
	
	@Column
	private String payerUsername;
	
	@OneToOne
	private Magazine magazine;
	
	@OneToOne
	private Article article;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(long id, PaymentType paymentType, Double price, Date date, String payerUsername, Magazine magazine,
			Article article) {
		super();
		this.id = id;
		this.paymentType = paymentType;
		this.price = price;
		this.date = date;
		this.payerUsername = payerUsername;
		this.magazine = magazine;
		this.article = article;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPayerUsername() {
		return payerUsername;
	}

	public void setPayerUsername(String payerUsername) {
		this.payerUsername = payerUsername;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
