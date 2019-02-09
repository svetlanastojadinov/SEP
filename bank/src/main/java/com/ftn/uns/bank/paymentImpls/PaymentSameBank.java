package com.ftn.uns.bank.paymentImpls;


import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
public class PaymentSameBank {

	@Value("${pc.front}")
	private String url;

	@Value("${pc.address}")
	private String pcAddress;

	@Autowired
	private ClientAccountService clientAccountService;

	@Autowired
	private ClientMerchantService clientMerchantService;

	@Autowired
	private TransactionService transactionService;

	public Map<String, Object> completingTransaction(ClientAccount clientAccount, long paymentId) {

		Transaction transaction = transactionService.findOne(paymentId);
		ClientMerchant clientMerchant = clientMerchantService.findOne(transaction.getMerchantId());
		Map<String, Object> response = new HashMap<String, Object>();

		ClientAccount clientFromDB = clientAccountService.findOne(clientAccount.getPan());

		System.err.println("ista banka: PLACAMOOO " + transaction.getAmount() + " od " + clientFromDB.getPan() + " za "
				+ clientMerchant.getClientAccount().getPan());

		clientAccountService.updateFunds(clientFromDB, clientFromDB.getAvailableFunds() - transaction.getAmount());
		clientAccountService.updateFunds(clientMerchant.getClientAccount(),
				clientMerchant.getClientAccount().getAvailableFunds() + transaction.getAmount());

		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String auth = "BANK" + ":" + "819a8bb3be2f87ddace7ea6b5ba156c0";
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

		MultiValueMap<String, String> map1 = new LinkedMultiValueMap<String, String>();
		((MultiValueMap<String, String>) map1).add("merchantOrderId", String.valueOf(transaction.getMerchantOrderId()));
		String result = rest.postForObject(pcAddress + "/api/card/complete/payment",
				new HttpEntity<MultiValueMap<String, String>>(map1, headers), String.class);

		if (result.contains("error")) {
			response.put("paymentStatus", "error");
			response.put("redirect_url", url + "/error");
		} else {
			response.put("status", "success");
			response.put("redirect_url", url + "/cardsuccess");
		}

		return response;

	}

}
