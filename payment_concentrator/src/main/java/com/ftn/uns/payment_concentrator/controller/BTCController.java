package com.ftn.uns.payment_concentrator.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.model.OrderViaBTC;

@RestController
@RequestMapping(value = "/api/bitcoin")
public class BTCController {

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> createOrder(@Valid @RequestBody Order order) {
		Map<String, Object> response = new HashMap<String, Object>();
		System.out.println("opa");
		OrderViaBTC orderViaBTC = new OrderViaBTC();
		/*
		 * if(order.getMagazine()==null){
		 * orderViaBTC.setOrder_id(Long.toString(order.getArticle().getId()));
		 * }else{ orderViaBTC.setOrder_id(order.getMagazine().getIssn()); }
		 * orderViaBTC.setPrice_amount(order.getPrice());
		 */
		orderViaBTC.setOrder_id("Merchant's ID");
		orderViaBTC.setPrice_amount(0.001);
		// orderViaBTC.setCallback_url("http://example.com/payments/accept-coingate-callback");
		orderViaBTC.setCancel_url("http://localhost:4200");
		orderViaBTC.setSuccess_url("http://localhost:4200");
		orderViaBTC.setToken("ssssssVw4VbDTLRfQBb9C7bixEtQeXzQPJENVy5r");

		String url = "https://api-sandbox.coingate.com/v2/orders";
		// CXaY7NVw4VbDTLRfQBb9C7bixEtQeXzQPJENVy5r sand
		// 8nQpsozzqQVTzgYicPx2eutAoDn4aLbr_SpYD83R token
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token CXaY7NVw4VbDTLRfQBb9C7bixEtQeXzQPJENVy5r");
		ResponseEntity<OrderViaBTC> responseEntity = new RestTemplate().exchange(url, HttpMethod.POST,
				new HttpEntity<OrderViaBTC>(orderViaBTC, headers), OrderViaBTC.class);

		if (responseEntity.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
			response.put("status", "error");
			return response;
		}
		response.put("status", "success");
		response.put("redirect_url", responseEntity.getBody().getPayment_url());

		return response;
	}

}
