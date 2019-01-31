package com.ftn.uns.payment_concentrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.model.Merchant;
import com.ftn.uns.payment_concentrator.service.MerchantService;

@RestController
@RequestMapping(value = "/api/merchants")
public class MerchantController {
	
	@Autowired
	private MerchantService merchantService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<Merchant> getMerchant(@PathVariable String id) {
		Merchant merchant = merchantService.findOne(id);
		return new ResponseEntity<Merchant>(merchant, HttpStatus.OK);
	}

}
