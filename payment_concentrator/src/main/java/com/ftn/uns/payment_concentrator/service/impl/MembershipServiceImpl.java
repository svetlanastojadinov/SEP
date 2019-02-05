package com.ftn.uns.payment_concentrator.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_concentrator.model.Membership;
import com.ftn.uns.payment_concentrator.repository.MembershipRepository;
import com.ftn.uns.payment_concentrator.service.MembershipService;

@Service
public class MembershipServiceImpl implements MembershipService{
	
	@Autowired
	private MembershipRepository membershipRepository;

	@Override
	public Optional<Membership> findOne(Long id) {
		return membershipRepository.findById(id);
	}

	@Override
	public List<Membership> findAll() {
		return membershipRepository.findAll();
	}

	@Override
	public Membership save(Membership membership) {
		return membershipRepository.save(membership);
	}

	@Override
	public void delete(Long id) {
		membershipRepository.deleteById(id);
		
	}

}
