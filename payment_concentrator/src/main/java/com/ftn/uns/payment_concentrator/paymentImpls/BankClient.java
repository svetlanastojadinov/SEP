package com.ftn.uns.payment_concentrator.paymentImpls;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.paymentInterface.PaymentInterface;

@Service
public class BankClient implements PaymentInterface {

	private String urlBank = "http://localhost:8083/api/transactions";// banka
																		// prodavca
	private String address = "http://localhost:4200";

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> create(Order order) {
		System.err.println("pc: placanje karticom");
		Map<String, Object> response = new HashMap<String, Object>();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<Map> responseEntity = new RestTemplate().exchange(urlBank, HttpMethod.POST,
				new HttpEntity<Order>(order, headers), Map.class);

		if (responseEntity.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
			response.put("status", "error");
			response.put("redirect_url", address + "/error");
			return response;
		}
		response.put("status", "success");
		response.put("redirect_url",
				responseEntity.getBody().get("url") + "/" + String.valueOf(responseEntity.getBody().get("paymentId")));

		return response;
	}

	@Override
	public Map<String, Object> complete(HttpServletRequest request) {
		System.out.println("pc: zavrseno placanje");

		return null;
	}

}
