package com.ftn.uns.payment_concentrator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_concentrator.exeptions.UnexistingMagazineException;
import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.repository.MagazineRepository;
import com.ftn.uns.payment_concentrator.service.MagazineService;

@Service
public class MagazineServiceJpa implements MagazineService{

	@Autowired
	private MagazineRepository magazineRepository;
	
	@Override
	public Magazine findOne(String issn) {
		// TODO Auto-generated method stub
		Magazine magazine=magazineRepository.findById(issn).orElseThrow(()->new UnexistingMagazineException(issn));
		
		return magazine;
	}

	@Override
	public List<Magazine> findAll() {
		// TODO Auto-generated method stub
		return magazineRepository.findAll();
	}

	@Override
	public Magazine save(Magazine magazine) {
		// TODO Auto-generated method stub
		return magazineRepository.save(magazine);
	}

	@Override
	public void delete(Magazine magazine) {
		// TODO Auto-generated method stub
		magazineRepository.delete(magazine);
	}

	@Override
	public Magazine update(Magazine magazine, String issn) {
		// TODO Auto-generated method stub
		Magazine magazineToUpdate=this.findOne(issn);
		magazineToUpdate.setSubscription(magazine.getSubscription());
		magazineToUpdate.setTitle(magazine.getTitle());
		
		return magazineRepository.save(magazineToUpdate);
	}

}
