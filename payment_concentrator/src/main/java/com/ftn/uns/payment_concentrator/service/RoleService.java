package com.ftn.uns.payment_concentrator.service;

import java.util.Optional;

import com.ftn.uns.payment_concentrator.model.role.Role;

public interface RoleService {
	Optional<Role> findOne(Long id);
}
