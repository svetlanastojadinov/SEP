package com.ftn.uns.pcc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.pcc.exceptions.UnexistingBankException;
import com.ftn.uns.pcc.model.Bank;
import com.ftn.uns.pcc.repository.BankRepository;
import com.ftn.uns.pcc.service.BankService;

@Service
public class BankJpa implements BankService {

	@Autowired
	private BankRepository bankRepository;

	@Override
	public Bank findOne(long id) {
		Bank bank = bankRepository.findById(id).orElseThrow(() -> new UnexistingBankException(id));

		return bank;
	}

	@Override
	public List<Bank> findAll() {
		// TODO Auto-generated method stub
		return bankRepository.findAll();
	}

	@Override
	public Bank save(Bank bank) {
		// TODO Auto-generated method stub
		return bankRepository.save(bank);
	}

	@Override
	public void delete(Bank bank) {
		// TODO Auto-generated method stub
		bankRepository.delete(bank);
	}

	@Override
	public Bank updateBank(Bank bank, long id) {
		// TODO Auto-generated method stub
		Bank bankToUpdate = this.findOne(id);
		bankToUpdate.setName(bank.getName());

		return bankRepository.save(bankToUpdate);
	}

}
