package com.ftn.uns.bank.service.impl;

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
public class PaymentSameBank {

	@Autowired
	private ClientAccountService clientAccountService;

	@Autowired
	private ClientMerchantService clientMerchantService;

	@Autowired
	private TransactionService transactionService;
	
	private String url="http://localhost:4200";

	public Map<String, Object> completeTransaction(ClientAccount clientAccount, long paymentId) {

		Transaction transaction = transactionService.findOne(paymentId);
		ClientMerchant clientMerchant = clientMerchantService.findOne(transaction.getMerchantId());
		Map<String, Object> response = new HashMap<String, Object>();

		ClientAccount clientFromDB = clientAccountService.findOne(clientAccount.getPan());
		
		if (clientFromDB == null) {
			// wrong pan, redirect
			response.put("status", "wrong data");
			response.put("redirect_url", url+"/cancelPaypal");
			return response;
		}
		if (!clientFromDB.getSecurityCode().equals(clientAccount.getSecurityCode())
				|| !clientFromDB.getCardHolderName().equals(clientAccount.getCardHolderName())) {
			// wrong data, redirect
			response.put("status", "wrong data");
			response.put("redirect_url", url+"/cancel");
			return response;
		}
		if (clientFromDB.getExpirationDate().before(new Date())) {
			// expired, redirect
			response.put("status", "expired");
			response.put("redirect_url", url+"/cancel");
			return response;
		}

		if (clientFromDB.getAvailableFunds() < transaction.getAmount()) {
			// client doesn't have enough money, redirect
			response.put("status", "error");
			response.put("redirect_url", url+"/cancel");
			return response;
		} else {
			System.err.println("PLACAMOOO " + transaction.getAmount() + " od " + clientFromDB.getPan() + " za "
					+ clientMerchant.getClientAccount().getPan());

			clientAccountService.updateFunds(clientFromDB, clientFromDB.getAvailableFunds() - transaction.getAmount());
			clientAccountService.updateFunds(clientMerchant.getClientAccount(),
					clientMerchant.getClientAccount().getAvailableFunds() + transaction.getAmount());
			response.put("status", "success");
			response.put("redirect_url", url+"/cardsuccess");
			

		}
		return response;

	}

}
