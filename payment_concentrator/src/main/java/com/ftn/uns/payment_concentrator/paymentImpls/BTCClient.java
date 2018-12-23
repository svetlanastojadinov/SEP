package com.ftn.uns.payment_concentrator.paymentImpls;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_concentrator.model.OrderViaBTC;
import com.ftn.uns.payment_concentrator.paymentInterface.PaymentInterface;

@Service
public class BTCClient implements PaymentInterface{

	@Override
	public Map<String, Object> create(String sum) {
		Map<String, Object> response = new HashMap<String, Object>();
		OrderViaBTC orderViaBTC = new OrderViaBTC();
		/*
		 * if(order.getMagazine()==null){
		 * orderViaBTC.setOrder_id(Long.toString(order.getArticle().getId()));
		 * }else{ orderViaBTC.setOrder_id(order.getMagazine().getIssn()); }
		 * orderViaBTC.setPrice_amount(order.getPrice());
		 */
		System.out.println("RADI LI?");
		orderViaBTC.setOrder_id("Merchant's ID");
		orderViaBTC.setPrice_amount(Double.parseDouble(sum));
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

	@Override
	public Map<String, Object> complete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
