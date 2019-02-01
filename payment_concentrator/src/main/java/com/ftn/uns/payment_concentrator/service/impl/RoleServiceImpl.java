package com.ftn.uns.payment_concentrator.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_concentrator.model.role.Role;
import com.ftn.uns.payment_concentrator.repository.RoleRepository;
import com.ftn.uns.payment_concentrator.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Optional<Role> findOne(Long id) {
		return roleRepository.findById(id);
	}

}
