package com.ftn.uns.payment_concentrator.service;

import java.util.List;
import java.util.Optional;

import com.ftn.uns.payment_concentrator.model.User;

public interface UserService {
	Optional<User> findOne(Long id);

	List<User> findAll();

	User save(User user);

	void delete(Long id);

	User findByUsername(String username);

	User findByEmail(String email);
}
