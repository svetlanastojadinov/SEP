package com.ftn.uns.bank.service;

import java.util.List;

import com.ftn.uns.bank.model.ClientMerchant;

public interface ClientMerchantService {
	
	public ClientMerchant findOne(String merchantId);
	
	public List<ClientMerchant> findAll();
	
	public ClientMerchant save(ClientMerchant clientMerchant);
	
	public void delete(ClientMerchant clientMerchant);
	
	public ClientMerchant update(ClientMerchant clientMerchant, String merchantId);

}
