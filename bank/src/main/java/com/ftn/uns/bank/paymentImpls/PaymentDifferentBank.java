package com.ftn.uns.bank.paymentImpls;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.bank.model.ClientAccount;
import com.ftn.uns.bank.model.ClientMerchant;
import com.ftn.uns.bank.model.Transaction;
import com.ftn.uns.bank.service.ClientAccountService;
import com.ftn.uns.bank.service.ClientMerchantService;
import com.ftn.uns.bank.service.TransactionService;

@Service
public class PaymentDifferentBank {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ClientAccountService clientAccountService;

	@Autowired
	private ClientMerchantService clientMerchantService;

	private String url = "http://localhost:4200";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> completingTransaction(Map map) {
		Map<String, Object> response = new HashMap<String, Object>();
		// proverava da li je zahtev ispravan?

		String pan = (String) ((Map<String, Object>) map.get("clientAccount")).get("pan");
		int transactionId = (int) map.get("ACQUIRER_ORDER_ID");
		Map<String, Object> clientAccount = (Map<String, Object>) map.get("clientAccount");

		Transaction transaction = transactionService.findOne((long) transactionId);
		ClientAccount clientFromDB = clientAccountService.findOne(pan);
		ClientMerchant clientMerchant = clientMerchantService.findOne(transaction.getMerchantId());

		if (clientFromDB == null) {
			response.put("status", "wrong data");
			response.put("redirect_url", url + "/error");
			return response;
		}
		if (!clientFromDB.getSecurityCode().equals(String.valueOf(clientAccount.get("securityCode")))
				|| !clientFromDB.getCardHolderName().equals(String.valueOf(clientAccount.get("cardHolderName")))) {
			response.put("status", "wrong data");
			response.put("redirect_url", url + "/error");
			return response;
		}

		if (clientFromDB.getAvailableFunds() < transaction.getAmount()) {
			response.put("status", "failed");
			response.put("redirect_url", url + "/failed");
			return response;
		} else {
			System.err.println("PLACAMOOO " + transaction.getAmount() + " od " + clientFromDB.getPan() + " za "
					+ clientMerchant.getClientAccount().getPan());

			// rezervisanje sredstva
			clientAccountService.updateFunds(clientFromDB, clientFromDB.getAvailableFunds() - transaction.getAmount());

			response.put("status", "success");
			response.put("ACQUIRER_ORDER_ID", map.get("ACQUIRER_ORDER_ID"));
			response.put("ACQUIRER_TIMESTAMP", map.get("ACQUIRER_TIMESTAMP"));
			response.put("ISSUER_ORDER_ID", map.get("ACQUIRER_ORDER_ID"));
			response.put("ISSUER_TIMESTAMP", new Date());

		}
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
		// kp: bankClient
		map.put("paymentStatus", "success");
		map.put("redirect_url", "/cardsuccess");
		return map;
	}
}
