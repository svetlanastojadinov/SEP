package com.ftn.uns.payment_concentrator.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.paymentImpls.BankClient;

@RestController
@RequestMapping(value = "api/card")
public class CardController {
	private final BankClient bankClient;

	@Autowired
	CardController(BankClient bankClient) {
		this.bankClient = bankClient;
	}

	@RequestMapping(value = "/make/payment", method = RequestMethod.POST)
	public Map<String, Object> createOrder(@RequestParam("sum") String sum) {
		return bankClient.create(sum);
	}

	@RequestMapping(value = "/complete/payment", method = RequestMethod.POST)
	public Map<String, Object> complete(HttpServletRequest request) {
		return bankClient.complete(request);
	}

}
