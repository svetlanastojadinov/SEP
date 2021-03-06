package com.ftn.uns.payment_concentrator.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class PaymentMethod implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column
	@Enumerated(EnumType.STRING)
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
