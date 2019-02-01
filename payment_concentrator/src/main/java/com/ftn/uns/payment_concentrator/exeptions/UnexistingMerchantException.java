package com.ftn.uns.payment_concentrator.exeptions;

public class UnexistingMerchantException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnexistingMerchantException(String id){
		super("Could not find merchant with id: "+id);
	}

}
