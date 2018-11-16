package com.ftn.uns.payment_concentrator.model;

import javax.persistence.*;

@Entity
public class PaymentMethod {

	@Id
	@Column
	private long id;

	@Column
	private PaymentType paymentType;

	public PaymentMethod() {
		super();
	}

	public PaymentMethod(long id, PaymentType paymentType) {
		super();
		this.id = id;
		this.paymentType = paymentType;
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

}
