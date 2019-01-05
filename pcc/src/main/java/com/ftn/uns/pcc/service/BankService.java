package com.ftn.uns.pcc.service;

import java.util.List;

import com.ftn.uns.pcc.model.Bank;

public interface BankService {

	public Bank findOne(long id);

	public List<Bank> findAll();

	public Bank save(Bank bank);

	public void delete(Bank bank);

	public Bank updateBank(Bank bank, long id);

}
