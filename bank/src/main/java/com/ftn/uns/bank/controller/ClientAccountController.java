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

import com.ftn.uns.bank.model.ClientAccount;
import com.ftn.uns.bank.service.ClientAccountService;

@RestController
@RequestMapping(value = "/api/clientAccounts")
public class ClientAccountController {

	@Autowired
	private ClientAccountService clientAccountService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<ClientAccount>> getClientAccounts() {

		ArrayList<ClientAccount> clientAccounts = (ArrayList<ClientAccount>) clientAccountService.findAll();
		if (clientAccounts != null) {
			return new ResponseEntity<Collection<ClientAccount>>(clientAccounts, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{pan}", method = RequestMethod.GET)
	private ResponseEntity<ClientAccount> getClientAccountByPan(@PathVariable String pan) {
		ClientAccount clientAccount = clientAccountService.findOne(pan);
		return new ResponseEntity<ClientAccount>(clientAccount, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<ClientAccount> saveClientAccount(@Valid @RequestBody ClientAccount clientAccount) {
		ClientAccount savedClientAccount = clientAccountService.save(clientAccount);
		return new ResponseEntity<ClientAccount>(savedClientAccount, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{pan}", method = RequestMethod.DELETE)
	private ResponseEntity<ClientAccount> deleteClientAccount(@PathVariable String pan) {
		clientAccountService.delete(clientAccountService.findOne(pan));
		return new ResponseEntity<ClientAccount>(HttpStatus.OK);
	}

}
