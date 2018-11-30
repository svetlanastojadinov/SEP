package com.ftn.uns.payment_concentrator.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_table")
public class Order {
	
	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	
	@Column
	private Double price;
	
	@Column
	private Date dateOfTransaction;
	
	@Column
	private String payerUsername;
	
	@OneToOne
	private Magazine magazine;
	
	@OneToOne
	private Article article;
	
	@Column
	private boolean executed;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(long id, PaymentType paymentType, Double price, Date date, String payerUsername, Magazine magazine,
			Article article, boolean executed) {
		super();
		this.id = id;
		this.paymentType = paymentType;
		this.price = price;
		this.dateOfTransaction = date;
		this.payerUsername = payerUsername;
		this.magazine = magazine;
		this.article = article;
		this.executed=executed;
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
		return dateOfTransaction;
	}

	public void setDate(Date date) {
		this.dateOfTransaction = date;
	}

	public Boolean getExecuted() {
		return executed;
	}

	public void setExecuted(Boolean executed) {
		this.executed = executed;
	}

}
