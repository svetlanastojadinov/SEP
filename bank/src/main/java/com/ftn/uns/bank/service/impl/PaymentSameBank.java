package com.ftn.uns.bank.service.impl;

import java.util.Date;

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

	public void completeTransaction(ClientAccount clientAccount, long paymentId) {

		Transaction transaction = transactionService.findOne(paymentId);
		ClientMerchant clientMerchant = clientMerchantService.findOne(transaction.getMerchantId());

		ClientAccount clientFromDB = clientAccountService.findOne(clientAccount.getPan());
		
		if (clientFromDB == null) {
			// wrong pan, redirect
		}
		if (!clientFromDB.getSecurityCode().equals(clientAccount.getSecurityCode())
				|| !clientFromDB.getCardHolderName().equals(clientAccount.getCardHolderName())) {
			// wrong data, redirect
		}
		if (clientFromDB.getExpirationDate().before(new Date())) {
			// expired, redirect
		}

		if (clientFromDB.getAvailableFunds() < transaction.getAmount()) {
			// client doesn't have enough money, redirect
		} else {
			System.err.println("PLACAMOOO " + transaction.getAmount() + " od " + clientFromDB.getPan() + " za "
					+ clientMerchant.getClientAccount().getPan());

			clientAccountService.updateFunds(clientFromDB, clientFromDB.getAvailableFunds() - transaction.getAmount());
			clientAccountService.updateFunds(clientMerchant.getClientAccount(),
					clientMerchant.getClientAccount().getAvailableFunds() + transaction.getAmount());

		}

	}

}
