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

		String pan = (String) ((Map<String, Object>) map.get("clientAccount")).get("pan");
		int transactionId = (int) map.get("ACQUIRER_ORDER_ID");

		Transaction transaction = transactionService.findOne((long) transactionId);
		ClientAccount clientFromDB = clientAccountService.findOne(pan);
		ClientMerchant clientMerchant = clientMerchantService.findOne(transaction.getMerchantId());

		System.err.println("PLACAMOOO " + transaction.getAmount() + " od " + clientFromDB.getPan() + " za "
				+ clientMerchant.getClientAccount().getPan());

		clientAccountService.updateFunds(clientFromDB, clientFromDB.getAvailableFunds() - transaction.getAmount());

		response.put("status", "success");
		response.put("ACQUIRER_ORDER_ID", map.get("ACQUIRER_ORDER_ID"));
		response.put("ACQUIRER_TIMESTAMP", map.get("ACQUIRER_TIMESTAMP"));
		response.put("ISSUER_ORDER_ID", map.get("ACQUIRER_ORDER_ID"));
		response.put("ISSUER_TIMESTAMP", new Date());

		return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> completeTransaction(Map map) {// za Merchanta
		System.err.println("bank: diff completed!!");

		int transactionId = (int) map.get("ACQUIRER_ORDER_ID");
		Transaction transaction = transactionService.findOne((long) transactionId);

		ClientMerchant clientMerchant = clientMerchantService.findOne(transaction.getMerchantId());

		clientAccountService.updateFunds(clientMerchant.getClientAccount(),
				clientMerchant.getClientAccount().getAvailableFunds() + transaction.getAmount());

		map.put("paymentStatus", "success");
		map.put("redirect_url", url + "/cardsuccess");
		return map;
	}
}
