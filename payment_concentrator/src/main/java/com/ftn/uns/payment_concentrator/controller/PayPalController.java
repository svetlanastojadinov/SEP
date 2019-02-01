package com.ftn.uns.payment_concentrator.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.paymentImpls.PayPalClient;

@RestController
@RequestMapping(value = "api/paypal")
public class PayPalController {

	private final PayPalClient payPalClient;

	@Autowired
	PayPalController(PayPalClient payPalClient) {
		this.payPalClient = payPalClient;
	}

	@PostMapping(value = "/make/payment")
	public Map<String, Object> makePayment(@RequestBody Order order) {
		return payPalClient.create(order);
	}

	@PostMapping(value = "/complete/payment")
	public Map<String, Object> completePayment(HttpServletRequest request) {
		return payPalClient.complete(request);
	}
}
