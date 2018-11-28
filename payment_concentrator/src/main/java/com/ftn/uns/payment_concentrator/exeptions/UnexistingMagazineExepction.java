package com.ftn.uns.payment_concentrator.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnexistingMagazineExepction extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnexistingMagazineExepction(String issn){
		super("Could not find magazine with issn: "+issn);
	}

}
