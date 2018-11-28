package com.ftn.uns.payment_concentrator.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ftn.uns.payment_concentrator.model.*;
import com.ftn.uns.payment_concentrator.service.MagazineService;

@RestController
@RequestMapping(value = "/api/magazines")
public class MagazineController {
	
	@Autowired
	private MagazineService magazineService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Magazine>> getMagazines() {
		
		ArrayList<Magazine> magazines = (ArrayList<Magazine>) magazineService.findAll();
		if (magazines != null) {
			return new ResponseEntity<Collection<Magazine>>(magazines, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
