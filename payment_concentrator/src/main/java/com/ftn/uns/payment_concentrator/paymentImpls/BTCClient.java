package com.ftn.uns.payment_concentrator.paymentImpls;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.model.OrderViaBTC;
import com.ftn.uns.payment_concentrator.paymentInterface.PaymentInterface;
import com.ftn.uns.payment_concentrator.service.OrderService;

@Service
public class BTCClient implements PaymentInterface {

	private String url = "http://localhost:4200";
	private String sandboxUrl = "https://api-sandbox.coingate.com/v2/orders";
	private String clientSecret = "Token CXaY7NVw4VbDTLRfQBb9C7bixEtQeXzQPJENVy5r";
	private String token;

	@Autowired
	private OrderService orderService;

	@Override
	public Map<String, Object> create(Order order) {
		Map<String, Object> response = new HashMap<String, Object>();
		OrderViaBTC orderViaBTC = new OrderViaBTC();

		orderViaBTC.setOrder_id("Merchant's ID");
		orderViaBTC.setPrice_amount(order.getAmount());
		orderViaBTC.setCancel_url(url + "/cancelbtc");
		token = UUID.randomUUID().toString();
		orderViaBTC.setSuccess_url(url + "/btcsuccess/" + token);
		// orderViaBTC.setCallback_url("http://localhost:8080/api/bitcoin/complete/payment");
		orderViaBTC.setToken(token);
		order.setToken(token);

		// CXaY7NVw4VbDTLRfQBb9C7bixEtQeXzQPJENVy5r sand
		// 8nQpsozzqQVTzgYicPx2eutAoDn4aLbr_SpYD83R token
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", clientSecret);
		ResponseEntity<OrderViaBTC> responseEntity = new RestTemplate().exchange(sandboxUrl, HttpMethod.POST,
				new HttpEntity<OrderViaBTC>(orderViaBTC, headers), OrderViaBTC.class);

		if (responseEntity.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
			order.setExecuted(false);
			orderService.save(order);
			response.put("status", "error");
			return response;
		}
		orderService.save(order);
		response.put("status", "success");
		response.put("redirect_url", responseEntity.getBody().getPayment_url());

		return response;
	}

	@Override
	public Map<String, Object> complete(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Order o=orderService.findOne(Long.parseLong(request.getParameter("paymentId")));
			orderService.updateExecution(o, true);
			System.out.println("btc: completed . . . ");
			response.put("status", "success");
		} catch (RuntimeException e) {
			response.put("status", "errror");
		}
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public Map<String, Object> createMembershipPaying(Magazine magazine) {
		// TODO Auto-generated method stub
		return null;
	}

}
