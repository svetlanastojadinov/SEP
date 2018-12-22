package com.ftn.uns.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.bank.exceptions.UnexistingClientMerchantException;
import com.ftn.uns.bank.model.ClientMerchant;
import com.ftn.uns.bank.repository.ClientMerchantRepository;
import com.ftn.uns.bank.service.ClientMerchantService;

@Service
public class ClientMerchantJpa implements ClientMerchantService{

	@Autowired
	private ClientMerchantRepository clientMerchantRepository;

	@Override
	public ClientMerchant findOne(String merchantId) {
		// TODO Auto-generated method stub
		ClientMerchant clientMerchant=clientMerchantRepository.findById(merchantId).orElseThrow(()-> new UnexistingClientMerchantException(merchantId));
		
		return clientMerchant;
	}

	@Override
	public List<ClientMerchant> findAll() {
		// TODO Auto-generated method stub
		return clientMerchantRepository.findAll();
	}

	@Override
	public ClientMerchant save(ClientMerchant clientMerchant) {
		// TODO Auto-generated method stub
		return clientMerchantRepository.save(clientMerchant);
	}

	@Override
	public void delete(ClientMerchant clientMerchant) {
		// TODO Auto-generated method stub
		clientMerchantRepository.delete(clientMerchant);
	}

	@Override
	public ClientMerchant update(ClientMerchant clientMerchant, String merchantId) {
		// TODO Auto-generated method stub
		ClientMerchant clientMerchantToUpdate=this.findOne(merchantId);
		clientMerchantToUpdate.getClientAccount().setAvailableFunds(clientMerchant.getClientAccount().getAvailableFunds());
		clientMerchantToUpdate.getClientAccount().setReservedFunds(clientMerchant.getClientAccount().getReservedFunds());

		return clientMerchantRepository.save(clientMerchantToUpdate);
	}

}
