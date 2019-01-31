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

	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long merchantOrderId;

	@Column
	private String buyerUsername;

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

	public Order(PaymentType paymentType, Double amount, Date merchantTimestamp, String merchantId,
			String merchantPassword, long merchantOrderId, String buyerUsername, Magazine magazine, Article article,
			boolean executed) {
		super();
		this.paymentType = paymentType;
		this.amount = amount;
		this.merchantTimestamp = merchantTimestamp;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.merchantOrderId = merchantOrderId;
		this.buyerUsername = buyerUsername;
		this.magazine = magazine;
		this.article = article;
		this.executed = executed;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getBuyerUsername() {
		return buyerUsername;
	}

	public void setBuyerUsername(String buyerUsername) {
		this.buyerUsername = buyerUsername;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
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

	public long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	@Override
	public String toString() {
		return "Order [paymentType=" + paymentType + ", amount=" + amount + ", merchantTimestamp=" + merchantTimestamp
				+ ", merchantId=" + merchantId + ", merchantPassword=" + merchantPassword + ", merchantOrderId="
				+ merchantOrderId + ", buyerUsername=" + buyerUsername + ", magazine=" + magazine + ", article="
				+ article + ", executed=" + executed + "]";
	}

}
