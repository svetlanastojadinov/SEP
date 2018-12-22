package com.ftn.uns.bank.exceptions;

public class UnexistingClientMerchantException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnexistingClientMerchantException(String merchantId){
		super("Could not find client merchant with merchantId: "+merchantId);
	}

}
