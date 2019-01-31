package com.ftn.uns.payment_concentrator.controller;

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

import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.service.MagazineService;

@RestController
@RequestMapping(value = "/api/magazines")
public class MagazineController {

	@Autowired
	private MagazineService magazineService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Magazine>> getMagazines() {

		ArrayList<Magazine> magazines = (ArrayList<Magazine>) magazineService.findAll();
		if (magazines != null) {
			return new ResponseEntity<Collection<Magazine>>(magazines, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{issn}", method = RequestMethod.GET)
	private ResponseEntity<Magazine> getMagazineByIssn(@PathVariable String issn) {
		Magazine magazine = magazineService.findOne(issn);
		return new ResponseEntity<Magazine>(magazine, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<Magazine> saveMagazine(@Valid @RequestBody Magazine magazine) {

		Magazine savedMagazine = magazineService.save(magazine);

		return new ResponseEntity<Magazine>(savedMagazine, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{issn}", method = RequestMethod.PUT)
	private ResponseEntity<Magazine> updateMagazine(@PathVariable String issn, @Valid @RequestBody Magazine magazine) {

		Magazine updatedMagazine = magazineService.update(magazine, issn);
		return new ResponseEntity<Magazine>(updatedMagazine, HttpStatus.OK);
	}

	@RequestMapping(value = "/{issn}", method = RequestMethod.DELETE)
	private ResponseEntity<Magazine> deleteMagazine(@PathVariable String issn) {

		magazineService.delete(magazineService.findOne(issn));

		return new ResponseEntity<Magazine>(HttpStatus.OK);
	}

}
