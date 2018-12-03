package com.ftn.uns.payment_concentrator.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnexistingPaymentMethodException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UnexistingPaymentMethodException(long id){
		super("Could not find payment method with id: "+id);
	}

}
