package com.ftn.uns.payment_concentrator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_concentrator.exeptions.UnexistingPaymentMethodException;
import com.ftn.uns.payment_concentrator.model.PaymentMethod;
import com.ftn.uns.payment_concentrator.repository.PaymentMethodRepository;
import com.ftn.uns.payment_concentrator.service.PaymentMethodService;

@Service
public class PaymentMethodServiceJpa implements PaymentMethodService{
	
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	@Override
	public PaymentMethod findOne(long id) {
		PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElseThrow(()-> new UnexistingPaymentMethodException(id));
		return paymentMethod;
	}

	@Override
	public List<PaymentMethod> findAll() {
		return paymentMethodRepository.findAll();
	}

	@Override
	public PaymentMethod save(PaymentMethod paymentMethod) {
		return paymentMethodRepository.save(paymentMethod);
	}

	@Override
	public void delete(PaymentMethod paymentMethod) {
		paymentMethodRepository.delete(paymentMethod);
		
	}

	@Override
	public PaymentMethod update(PaymentMethod paymentMethod, long id) {
		// ovo cemo kasnije. nije toliko bitno 
		return null;
	}

}
