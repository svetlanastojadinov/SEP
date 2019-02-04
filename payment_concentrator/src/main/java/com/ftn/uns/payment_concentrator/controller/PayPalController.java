package com.ftn.uns.payment_concentrator.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	private String currentIndicator;
	private String currentIdentificator;

	@Autowired
	PayPalController(PayPalClient payPalClient) {
		this.payPalClient = payPalClient;
	}

	@PostMapping(value = "/make/payment")
	public Map<String, Object> makePayment(@RequestBody Order order) {
		if(order.getArticle() != null) {
			currentIndicator = "article";
			currentIdentificator = Long.toString(order.getArticle().getId());
		}
		if(order.getMagazine() != null) {
			currentIndicator = "magazine";
			currentIdentificator = order.getMagazine().getIssn();
		}
		return payPalClient.create(order);
	}

	@PostMapping(value = "/complete/payment")
	public ResponseEntity<?> completePayment(HttpServletRequest request) {
		//request.setAttribute("currentIndicator", currentIndicator);
		//request.setAttribute("currentIdentificator", currentIdentificator);
		Map<String, Object> retVal = payPalClient.complete(request);
		return new ResponseEntity<>("KRAAAj",HttpStatus.OK);
				
	}
}
