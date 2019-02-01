package com.ftn.uns.bank.paymentImpls;

import java.util.Date;
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

import com.ftn.uns.bank.model.ClientAccount;
import com.ftn.uns.bank.model.Transaction;
import com.ftn.uns.bank.service.ClientAccountService;
import com.ftn.uns.bank.service.TransactionService;

@Service
public class Payment {
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ClientAccountService clientAccountService;

	@Autowired
	private PaymentSameBank paymentSameBank;

	private String bin = "438131"; // unic banka

	private String urlPcc = "http://localhost:8082/api/banks/payment";

	private String url = "http://localhost:4200";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> startTransaction(long paymentId, ClientAccount clientAccount) {
		Map<String, Object> response = new HashMap<String, Object>();

		Transaction transaction = transactionService.findOne((long) paymentId);
		ClientAccount clientFromDB = clientAccountService.findOne(clientAccount.getPan());

		if (clientFromDB == null) {
			response.put("status", "wrong data");
			response.put("redirect_url", url + "/wrongdata");
			return response;
		}
		if (!clientFromDB.getSecurityCode().equals(clientAccount.getSecurityCode())
				|| !clientFromDB.getCardHolderName().equals(clientAccount.getCardHolderName())) {
			response.put("status", "wrong data");
			response.put("redirect_url", url + "/wrongdata");
			return response;
		}

		if (clientFromDB.getAvailableFunds() < transaction.getAmount()) {
			response.put("status", "failed");
			response.put("redirect_url", url + "/failed");
			return response;
		}

		if (clientAccount.getPan().startsWith(bin)) {
			response = paymentSameBank.completingTransaction(clientAccount, paymentId);
		} else {
			Map<String, Object> request = new HashMap<String, Object>();
			request.put("ACQUIRER_ORDER_ID", paymentId);
			request.put("ACQUIRER_TIMESTAMP", new Date());
			request.put("clientAccount", clientAccount);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			ResponseEntity<Map> responseEntity = new RestTemplate().exchange(urlPcc, HttpMethod.POST,
					new HttpEntity<Map>(request, headers), Map.class);
			response = responseEntity.getBody();
		}

		return response;
	}
}
