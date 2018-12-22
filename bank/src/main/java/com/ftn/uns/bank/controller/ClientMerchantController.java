package com.ftn.uns.bank.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.bank.model.ClientMerchant;
import com.ftn.uns.bank.service.ClientMerchantService;

@RestController
@RequestMapping(value = "/api/clientMerchants")
public class ClientMerchantController {

	@Autowired
	private ClientMerchantService clientMerchantService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<ClientMerchant>> getClientMerchants() {

		ArrayList<ClientMerchant> clientMerchants = (ArrayList<ClientMerchant>) clientMerchantService.findAll();
		if (clientMerchants != null) {
			return new ResponseEntity<Collection<ClientMerchant>>(clientMerchants, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{merchantId}", method = RequestMethod.GET)
	private ResponseEntity<ClientMerchant> getClientMerchanttById(@PathVariable String merchantId) {
		ClientMerchant clientMerchant = clientMerchantService.findOne(merchantId);
		return new ResponseEntity<ClientMerchant>(clientMerchant, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<ClientMerchant> saveClientMerchant(@Valid @RequestBody ClientMerchant clientMerchant) {
		ClientMerchant savedClientMerchant = clientMerchantService.save(clientMerchant);
		return new ResponseEntity<ClientMerchant>(savedClientMerchant, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{merchantId}", method = RequestMethod.DELETE)
	private ResponseEntity<ClientMerchant> deleteClientMerchant(@PathVariable String merchantId) {
		clientMerchantService.delete(clientMerchantService.findOne(merchantId));
		return new ResponseEntity<ClientMerchant>(HttpStatus.OK);
	}

}
