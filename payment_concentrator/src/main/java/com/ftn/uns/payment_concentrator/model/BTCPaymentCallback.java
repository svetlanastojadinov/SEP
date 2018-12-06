package com.ftn.uns.payment_concentrator.model;

import java.util.Date;

public class BTCPaymentCallback {
	private long id;
	private long order_id;
	private BTCOrderStatus status;
	private double price_amount;
	private String receive_currency;
	private double pay_amount;
	private String pay_currency;
	private Date created_at;
	private String token;

	public BTCPaymentCallback() {
	}

	public BTCPaymentCallback(long id, long order_id, BTCOrderStatus status, double price_amount,
			String receive_currency, double pay_amount, String pay_currency, Date created_at, String token) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.status = status;
		this.price_amount = price_amount;
		this.receive_currency = receive_currency;
		this.pay_amount = pay_amount;
		this.pay_currency = pay_currency;
		this.created_at = created_at;
		this.token = token;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public BTCOrderStatus getStatus() {
		return status;
	}

	public void setStatus(BTCOrderStatus status) {
		this.status = status;
	}

	public double getPrice_amount() {
		return price_amount;
	}

	public void setPrice_amount(double price_amount) {
		this.price_amount = price_amount;
	}

	public String getReceive_currency() {
		return receive_currency;
	}

	public void setReceive_currency(String receive_currency) {
		this.receive_currency = receive_currency;
	}

	public double getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(double pay_amount) {
		this.pay_amount = pay_amount;
	}

	public String getPay_currency() {
		return pay_currency;
	}

	public void setPay_currency(String pay_currency) {
		this.pay_currency = pay_currency;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
