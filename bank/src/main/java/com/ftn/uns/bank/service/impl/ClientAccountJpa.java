package com.ftn.uns.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.bank.exceptions.UnexistingClientAccountException;
import com.ftn.uns.bank.model.ClientAccount;
import com.ftn.uns.bank.repository.ClientAccountRepository;
import com.ftn.uns.bank.service.ClientAccountService;

@Service
public class ClientAccountJpa implements ClientAccountService {

	@Autowired
	private ClientAccountRepository clientAccountRepository;

	@Override
	public ClientAccount findOne(String pan) {
		// TODO Auto-generated method stub
		ClientAccount clientAccount = clientAccountRepository.findById(pan)
				.orElseThrow(() -> new UnexistingClientAccountException(pan));

		return clientAccount;
	}

	@Override
	public List<ClientAccount> findAll() {
		// TODO Auto-generated method stub
		return clientAccountRepository.findAll();
	}

	@Override
	public ClientAccount save(ClientAccount clientAccount) {
		// TODO Auto-generated method stub
		return clientAccountRepository.save(clientAccount);
	}

	@Override
	public void delete(ClientAccount clientAccount) {
		// TODO Auto-generated method stub
		clientAccountRepository.delete(clientAccount);
	}

	@Override
	public ClientAccount updateFunds(ClientAccount clientAccount,double funds) {
		// TODO Auto-generated method stub
		ClientAccount clientAccountToUpdate = this.findOne(clientAccount.getPan());
		clientAccountToUpdate.setAvailableFunds(funds);
		
		return clientAccountRepository.save(clientAccountToUpdate);
	}


}
