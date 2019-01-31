package com.ftn.uns.bank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Transaction {

	@Id
	@Column(nullable = false, unique = true, length=10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String merchantId;

	@Column
	private String merchantPassword;

	@Column
	private long merchantOrderId;

	@Column
	private Date merchantTimestamp;

	@Column
	private double amount;

	@Column
	private String successUrl;
	
	@Column
	private String failedUrl;
	
	@Column
	private String errorUrl;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(long id, String merchantId, String merchantPassword, int merchantOrderId, Date merchantTimestamp,
			double amount, String successUrl, String failedUrl, String errorUrl) {
		super();
		this.id = id;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.amount = amount;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", merchantId=" + merchantId + ", merchantPassword=" + merchantPassword
				+ ", merchantOrderId=" + merchantOrderId + ", merchantTimestamp=" + merchantTimestamp + ", amount="
				+ amount + ", successUrl=" + successUrl + ", failedUrl=" + failedUrl + ", errorUrl=" + errorUrl + "]";
	}

}
