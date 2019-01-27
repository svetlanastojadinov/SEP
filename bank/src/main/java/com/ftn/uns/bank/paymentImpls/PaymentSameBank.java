package com.ftn.uns.bank.paymentImpls;

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
public class PaymentSameBank {

	@Autowired
	private ClientAccountService clientAccountService;

	@Autowired
	private ClientMerchantService clientMerchantService;

	@Autowired
	private TransactionService transactionService;

	private String url = "http://localhost:4200";

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
		response.put("status", "success");
		response.put("redirect_url", url + "/cardsuccess");

		return response;

	}

}
