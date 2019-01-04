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
@Table(name = "order_table")
public class Order {

	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	@Column
	private Double amount;

	@Column
	private Date merchantTimestamp;

	@Column
	private String merchantId;

	@Column
	private String merchantPassword;

	@Column
	private String merchantOrderId;

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

	public Order(long id, PaymentType paymentType, Double amount, Date merchantTimestamp, String merchantId,
			String merchantPassword, String merchantOrderId, Magazine magazine, Article article, boolean executed) {
		super();
		this.id = id;
		this.paymentType = paymentType;
		this.amount = amount;
		this.merchantTimestamp = merchantTimestamp;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.merchantOrderId = merchantOrderId;
		this.magazine = magazine;
		this.article = article;
		this.executed = executed;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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
		return merchantTimestamp;
	}

	public void setDate(Date date) {
		this.merchantTimestamp = date;
	}

	public Boolean getExecuted() {
		return executed;
	}

	public void setExecuted(Boolean executed) {
		this.executed = executed;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", paymentType=" + paymentType + ", amount=" + amount + ", dateOfTransaction="
				+ merchantTimestamp + ", merchantId=" + merchantId + ", merchantPassword=" + merchantPassword
				+ ", merchantOrderId=" + merchantOrderId + ", magazine=" + magazine + ", article=" + article
				+ ", executed=" + executed + "]";
	}

}
