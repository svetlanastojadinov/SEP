package com.ftn.uns.payment_concentrator.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ftn.uns.payment_concentrator.dto.UserDTO;
import com.ftn.uns.payment_concentrator.model.User;
import com.ftn.uns.payment_concentrator.model.role.Role;

public class UserConverter {

	public UserConverter(){}
	
	public User DtoToUser(UserDTO dto,Role role) {
		return new User(dto.getUsername(), new BCryptPasswordEncoder().encode(dto.getPassword()), dto.getEmail(), dto.getName(), dto.getSurname(), role);
	}
}
