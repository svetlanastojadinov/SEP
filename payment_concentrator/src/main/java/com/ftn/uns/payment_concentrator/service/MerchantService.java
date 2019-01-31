package com.ftn.uns.payment_concentrator.service;

import java.util.List;

import com.ftn.uns.payment_concentrator.model.Merchant;

public interface MerchantService {
	public Merchant findOne(String merchantId);

	public List<Merchant> findAll();

	public Merchant save(Merchant merchant);

	public void delete(Merchant merchant);

	public Merchant update(Merchant merchant, String merchantId);

}
