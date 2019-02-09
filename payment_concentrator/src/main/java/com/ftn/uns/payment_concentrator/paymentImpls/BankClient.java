package com.ftn.uns.payment_concentrator.paymentImpls;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.paymentInterface.PaymentInterface;
import com.ftn.uns.payment_concentrator.service.OrderService;

@Service
public class BankClient implements PaymentInterface {

	@Value("${pc.front}")
	private String pcFront;

	@Value("${acquirer.address}")
	private String acquirerBank;

	@Autowired
	private OrderService orderService;

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> create(Order order) {
		System.err.println("pc: placanje karticom");
		Map<String, Object> response = new HashMap<String, Object>();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		orderService.save(order);

		ResponseEntity<Map> responseEntity = new RestTemplate().exchange(acquirerBank + "/api/transactions",
				HttpMethod.POST, new HttpEntity<Order>(order, headers), Map.class);

		if (responseEntity.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
			response.put("status", "error");
			response.put("redirect_url", pcFront + "/error");
			return response;
		}
		response.put("status", "success");
		response.put("redirect_url",
				responseEntity.getBody().get("url") + "/" + String.valueOf(responseEntity.getBody().get("paymentId")));

		return response;
	}

	@Override
	public Map<String, Object> complete(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Order order = orderService.findOne(Long.parseLong(request.getParameter("merchantOrderId")));
			orderService.updateExecution(order, true);
			System.err.println("pc: zavrseno placanje!");
			response.put("status", "success");
			response.put("redirect_url", pcFront + "/cardsuccess");

		} catch (RuntimeException e) {
			response.put("status", "errror");
		}
		return response;
	}

	@Override
	public Map<String, Object> createMembershipPaying(Magazine magazine) {
		// TODO Auto-generated method stub
		return null;
	}

}
