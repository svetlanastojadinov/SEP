package com.ftn.uns.pcc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.pcc.model.Bank;
import com.ftn.uns.pcc.paymentImpl.PaymentPcc;
import com.ftn.uns.pcc.service.BankService;

@RestController
@RequestMapping(value = "/api/banks")
public class BankController {
	@Autowired
	private BankService bankService;

	@Autowired
	private PaymentPcc paymentPcc;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Bank>> getBanks() {

		ArrayList<Bank> banks = (ArrayList<Bank>) bankService.findAll();
		if (banks != null) {
			return new ResponseEntity<Collection<Bank>>(banks, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<Bank> getBank(@PathVariable long id) {
		Bank bank = bankService.findOne(id);
		return new ResponseEntity<Bank>(bank, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<Bank> saveBank(@Valid @RequestBody Bank bank) {
		Bank savedBank = bankService.save(bank);
		return new ResponseEntity<Bank>(savedBank, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<Bank> deleteBank(@PathVariable long id) {
		bankService.delete(bankService.findOne(id));
		return new ResponseEntity<Bank>(HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	private ResponseEntity<Map> payment(@RequestBody Map map) {
		Map<String, Object> responseMap = paymentPcc.completingTransaction(map);

		return new ResponseEntity<Map>(responseMap, HttpStatus.OK);
	}

}
