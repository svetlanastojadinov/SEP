package com.ftn.uns.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class PaymentCallback {

	@Column
	private String paymentUrl;
	
	@Column
	@Id
	private long paymentId;
	
	public PaymentCallback(){}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	
	
}
