package com.ftn.uns.bank.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.bank.model.ClientAccount;
import com.ftn.uns.bank.model.PaymentCallback;
import com.ftn.uns.bank.model.Transaction;
import com.ftn.uns.bank.service.TransactionService;
import com.ftn.uns.bank.service.impl.PaymentSameBank;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private PaymentSameBank paymentSameBank;

	private String bankCode = "1";

	private String url = "http://localhost:1337";

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Transaction>> getTransactions() {

		ArrayList<Transaction> transactions = (ArrayList<Transaction>) transactionService.findAll();
		if (transactions != null) {
			return new ResponseEntity<Collection<Transaction>>(transactions, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<Transaction> getTransactionById(@PathVariable long id) {
		Transaction transaction = transactionService.findOne(id);
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<Transaction> deleteTransaction(@PathVariable long id) {
		transactionService.delete(transactionService.findOne(id));
		return new ResponseEntity<Transaction>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<Map<String, Object>> startTransaction(@RequestBody Transaction transaction) {

		Transaction savedTransaction = transactionService.save(transaction);

		PaymentCallback paymentCallback = new PaymentCallback();
		paymentCallback.setPaymentId(savedTransaction.getId());
		paymentCallback.setPaymentUrl(url);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("paymentId", paymentCallback.getPaymentId());
		response.put("url", paymentCallback.getPaymentUrl());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{paymentId}", method = RequestMethod.POST)
	public Map<String, Object> completeTransaction(@PathVariable long paymentId,
			@Valid @RequestBody ClientAccount clientAccount) {
		Map<String, Object> response = new HashMap<String, Object>();

		if (clientAccount.getPan().startsWith(bankCode)) {
			response = paymentSameBank.completeTransaction(clientAccount, paymentId);
		} else {
			// pcc
		}

		return response;
	}

}
