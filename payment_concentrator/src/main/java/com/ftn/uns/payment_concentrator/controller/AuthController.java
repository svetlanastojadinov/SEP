package com.ftn.uns.payment_concentrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.converter.UserConverter;
import com.ftn.uns.payment_concentrator.dto.UserDTO;
import com.ftn.uns.payment_concentrator.dto.responseToken.JwtAuthenticationResponse;
import com.ftn.uns.payment_concentrator.dto.responseToken.LoginRequestDTO;
import com.ftn.uns.payment_concentrator.model.User;
import com.ftn.uns.payment_concentrator.security.JwtTokenProvider;
import com.ftn.uns.payment_concentrator.service.RoleService;
import com.ftn.uns.payment_concentrator.service.UserService;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	private UserConverter userConverter = new UserConverter();

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody UserDTO dto) {
		return registerFoo(dto,1);
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO dto) {
		User user = userService.findByUsername(dto.getUsername());
		if (user == null) {
			return new ResponseEntity<>("USERNAME_NOT_EXIST", HttpStatus.BAD_REQUEST);
		}

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		return new ResponseEntity<>(new JwtAuthenticationResponse(jwt, user.getRole().getName().toString()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/registerAuthor", method=RequestMethod.POST)
	public ResponseEntity<?> registerAuthor(@RequestBody UserDTO dto) {
		return registerFoo(dto,2);
	}
	
	@RequestMapping(value="/registerRedactor", method=RequestMethod.POST)
	public ResponseEntity<?> registerRedactor(@RequestBody UserDTO dto) {
		return registerFoo(dto,3);
	}
	
	
	
	
	
	public ResponseEntity<?> registerFoo(UserDTO dto, int role){
		if (userService.findByUsername(dto.getUsername()) != null) {
			return new ResponseEntity<>("A user with the given username already exists", HttpStatus.BAD_REQUEST);
		}

		if (userService.findByEmail(dto.getEmail()) != null) {
			return new ResponseEntity<>("A user with the given email already exists", HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user = userConverter.DtoToUser(dto, roleService.findOne(Long.valueOf(role)).get());
		userService.save(user);
		return new ResponseEntity<>("Succesfully registred", HttpStatus.OK);
	}
}
