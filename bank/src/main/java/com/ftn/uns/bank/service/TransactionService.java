package com.ftn.uns.bank.service;

import java.util.List;

import com.ftn.uns.bank.model.Transaction;

public interface TransactionService {

	public Transaction findOne(long id);

	public List<Transaction> findAll();

	public Transaction save(Transaction transaction);

	public void delete(Transaction transaction);

	public Transaction update(Transaction transaction);
}
