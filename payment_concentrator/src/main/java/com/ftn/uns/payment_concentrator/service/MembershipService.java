package com.ftn.uns.payment_concentrator.service;

import java.util.List;
import java.util.Optional;

import com.ftn.uns.payment_concentrator.model.Membership;


public interface MembershipService {
	
	public Optional<Membership> findOne(Long id);

	public List<Membership> findAll();

	public Membership save(Membership membership);

	public void delete(Long id);

}
