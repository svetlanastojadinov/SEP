package com.ftn.uns.payment_concentrator.service;

import java.util.List;

import com.ftn.uns.payment_concentrator.model.PaymentMethod;


public interface PaymentMethodService {
	public PaymentMethod findOne(long id);

	public List<PaymentMethod> findAll();

	public PaymentMethod save(PaymentMethod paymentMethod);

	public void delete(PaymentMethod paymentMethod);

	public PaymentMethod update(PaymentMethod paymentMethod, long id);
}
