package com.ftn.uns.payment_concentrator.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.model.PaymentMethod;
import com.ftn.uns.payment_concentrator.service.PaymentMethodService;

@RestController
@RequestMapping(value = "/api/mpayment_methods")
public class PaymentMethodController {
	
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<PaymentMethod>> getPaymentMethods() {

		ArrayList<PaymentMethod> payment_methods = (ArrayList<PaymentMethod>) paymentMethodService.findAll();
		if (payment_methods != null) {
			return new ResponseEntity<Collection<PaymentMethod>>(payment_methods, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable long id) {
		PaymentMethod paymentMethod = paymentMethodService.findOne(id);
		return new ResponseEntity<PaymentMethod>(paymentMethod, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<PaymentMethod> saveOrder(@Valid @RequestBody PaymentMethod paymentMethod) {
		PaymentMethod savePaymentMethod = paymentMethodService.save(paymentMethod);
		return new ResponseEntity<PaymentMethod>(savePaymentMethod, HttpStatus.CREATED);
	}

}
