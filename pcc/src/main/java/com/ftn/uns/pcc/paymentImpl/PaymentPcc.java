package com.ftn.uns.pcc.paymentImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.pcc.model.Bank;
import com.ftn.uns.pcc.service.BankService;

@Service
public class PaymentPcc {

	@Autowired
	private BankService bankService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> completingTransaction(Map map) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		System.err.println("pcc: primila zahtev");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// provera da li je zahtev ispravan?
		String pan = (String) ((Map<String, Object>) map.get("clientAccount")).get("pan");
		ArrayList<Bank> banks = (ArrayList<Bank>) bankService.findAll();
		for (Bank bank : banks) {
			if (pan.startsWith(String.valueOf(bank.getBin()))) {
				String url = "http://localhost:" + String.valueOf(bank.getPort()) + "/api/transactions/completing";

				ResponseEntity<Map> responseEntity = new RestTemplate().exchange(url, HttpMethod.POST,
						new HttpEntity<Map>(map, headers), Map.class);
				if (responseEntity.getBody().get("status").equals("success")) {
					String url1 = "http://localhost:" + String.valueOf(bank.getPort()) + "/api/transactions/complete";

					ResponseEntity<Map> responseEnt = new RestTemplate().exchange(url1, HttpMethod.POST,
							new HttpEntity<Map>(responseEntity.getBody(), headers), Map.class);
					responseMap = responseEnt.getBody();
				} else {
					responseMap = responseEntity.getBody();
				}
			}
		}
		return responseMap;
	}

}
