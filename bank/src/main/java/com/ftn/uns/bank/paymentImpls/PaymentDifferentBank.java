package com.ftn.uns.bank.paymentImpls;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.bank.model.ClientAccount;
import com.ftn.uns.bank.model.ClientMerchant;
import com.ftn.uns.bank.model.Transaction;
import com.ftn.uns.bank.service.ClientAccountService;
import com.ftn.uns.bank.service.ClientMerchantService;
import com.ftn.uns.bank.service.TransactionService;

@Service
public class PaymentDifferentBank {
	
	@Value("${pc.front}")
	private String url;

	@Value("${pc.address}")
	private String pcAddress;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ClientAccountService clientAccountService;

	@Autowired
	private ClientMerchantService clientMerchantService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> completingTransaction(Map map) {
		Map<String, Object> response = new HashMap<String, Object>();

		String pan = (String) ((Map<String, Object>) map.get("clientAccount")).get("pan");

		Transaction transaction = new Transaction();
		Double amount = (Double) ((Map<String, Object>) map.get("transaction")).get("amount");
		String merchantId = (String) ((Map<String, Object>) map.get("transaction")).get("merchantId");
		String merchantPass = (String) ((Map<String, Object>) map.get("transaction")).get("merchantPassword");

		transaction.setAmount(amount);
		transaction.setMerchantId(merchantId);
		transaction.setMerchantPassword(merchantPass);

		transactionService.save(transaction);
		ClientAccount clientFromDB = clientAccountService.findOne(pan);

		clientAccountService.updateFunds(clientFromDB, clientFromDB.getAvailableFunds() - transaction.getAmount());

		response.put("status", "success");
		response.put("ACQUIRER_ORDER_ID", map.get("ACQUIRER_ORDER_ID"));
		response.put("ACQUIRER_TIMESTAMP", map.get("ACQUIRER_TIMESTAMP"));

		return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> completeTransaction(Map map) {
		System.err.println("bank: diff completed!!");

		int transactionId = (int) map.get("ACQUIRER_ORDER_ID");
		Transaction transaction = transactionService.findOne((long) transactionId);

		ClientMerchant clientMerchant = clientMerchantService.findOne(transaction.getMerchantId());

		clientAccountService.updateFunds(clientMerchant.getClientAccount(),
				clientMerchant.getClientAccount().getAvailableFunds() + transaction.getAmount());

		map.put("paymentStatus", "success");
		map.put("merchantOrderId", transaction.getMerchantOrderId());
		map.put("redirect_url", url + "/cardsuccess");
		map.put("ISSUER_ORDER_ID", map.get("ACQUIRER_ORDER_ID"));
		map.put("ISSUER_TIMESTAMP", new Date());

		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, String> map1 = new LinkedMultiValueMap<String, String>();
		((MultiValueMap<String, String>) map1).add("merchantOrderId", String.valueOf(transaction.getMerchantOrderId()));
		String result = rest.postForObject(pcAddress + "/api/card/complete/payment", map1, String.class);

		if (result.contains("error")) {
			map.put("paymentStatus", "error");
			map.put("redirect_url", url + "/error");
		}

		return map;
	}
}
