package com.ftn.uns.bank.service;

import java.util.List;

import com.ftn.uns.bank.model.ClientAccount;

public interface ClientAccountService {

	public ClientAccount findOne(String pan);

	public List<ClientAccount> findAll();

	public ClientAccount save(ClientAccount clientAccount);

	public void delete(ClientAccount clientAccount);

	public ClientAccount updateFunds(ClientAccount clientAccount, double funds);

}
