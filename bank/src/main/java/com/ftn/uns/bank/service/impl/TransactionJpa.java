package com.ftn.uns.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.bank.exceptions.UnexistingTransactionException;
import com.ftn.uns.bank.model.Transaction;
import com.ftn.uns.bank.repository.TransactionRepository;
import com.ftn.uns.bank.service.TransactionService;

@Service
public class TransactionJpa implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction findOne(long id) {
		// TODO Auto-generated method stub
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new UnexistingTransactionException(id));

		return transaction;
	}

	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

	@Override
	public Transaction save(Transaction transaction) {
		// TODO Auto-generated method stub
		return transactionRepository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) {
		// TODO Auto-generated method stub
		transactionRepository.delete(transaction);
	}

	@Override
	public Transaction update(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

}
