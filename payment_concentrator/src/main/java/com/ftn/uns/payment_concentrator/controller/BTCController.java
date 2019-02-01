package com.ftn.uns.payment_concentrator.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.paymentImpls.BTCClient;

@RestController
@RequestMapping(value = "api/bitcoin")
public class BTCController {

	private final BTCClient bTCClient;

	@Autowired
	BTCController(BTCClient bTCClient) {
		this.bTCClient = bTCClient;
	}

	@RequestMapping(value = "/make/payment", method = RequestMethod.POST)
	public Map<String, Object> createOrder(@RequestBody Order order) {
		return bTCClient.create(order);
	}

	@RequestMapping(value = "/complete/payment", method = RequestMethod.POST)
	public Map<String, Object> complete(HttpServletRequest request) {
		return bTCClient.complete(request);
	}

}
