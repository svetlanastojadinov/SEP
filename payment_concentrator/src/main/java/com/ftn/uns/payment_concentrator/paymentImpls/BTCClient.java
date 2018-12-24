package com.ftn.uns.payment_concentrator.paymentImpls;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
public class BTCClient implements PaymentInterface {

	private String url = "http://localhost:4200";
	private String sandboxUrl = "https://api-sandbox.coingate.com/v2/orders";
	private String clientSecret = "Token CXaY7NVw4VbDTLRfQBb9C7bixEtQeXzQPJENVy5r";
	private String token = UUID.randomUUID().toString();

	@Override
	public Map<String, Object> create(String sum) {
		System.err.println("create btc");
		Map<String, Object> response = new HashMap<String, Object>();
		OrderViaBTC orderViaBTC = new OrderViaBTC();

		orderViaBTC.setOrder_id("Merchant's ID");
		orderViaBTC.setPrice_amount(Double.parseDouble(sum));
		orderViaBTC.setCancel_url(url + "/cancelbtc");
		orderViaBTC.setSuccess_url(url + "/btcsucces");
		// orderViaBTC.setCallback_url("http://localhost:8080/api/bitcoin/complete/payment");
		orderViaBTC.setToken(token);

		// CXaY7NVw4VbDTLRfQBb9C7bixEtQeXzQPJENVy5r sand
		// 8nQpsozzqQVTzgYicPx2eutAoDn4aLbr_SpYD83R token
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", clientSecret);
		ResponseEntity<OrderViaBTC> responseEntity = new RestTemplate().exchange(sandboxUrl, HttpMethod.POST,
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
		System.err.println("COMPLETE ? ? ?");
		// TODO Auto-generated method stub
		return null;
	}

}
