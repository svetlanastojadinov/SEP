package com.ftn.uns.payment_concentrator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_concentrator.exeptions.UnexistingMerchantException;
import com.ftn.uns.payment_concentrator.model.Merchant;
import com.ftn.uns.payment_concentrator.repository.MerchantRepository;
import com.ftn.uns.payment_concentrator.service.MerchantService;

@Service
public class MerchantServiceJpa implements MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;

	@Override
	public Merchant findOne(String merchantId) {
		// TODO Auto-generated method stub
		Merchant merchant = merchantRepository.findById(merchantId)
				.orElseThrow(() -> new UnexistingMerchantException(merchantId));
		return merchant;
	}

	@Override
	public List<Merchant> findAll() {
		// TODO Auto-generated method stub
		return merchantRepository.findAll();
	}

	@Override
	public Merchant save(Merchant merchant) {
		// TODO Auto-generated method stub
		return merchantRepository.save(merchant);
	}

	@Override
	public void delete(Merchant merchant) {
		// TODO Auto-generated method stub

	}

	@Override
	public Merchant update(Merchant merchant, String merchantId) {
		// TODO Auto-generated method stub
		return null;
	}

}
