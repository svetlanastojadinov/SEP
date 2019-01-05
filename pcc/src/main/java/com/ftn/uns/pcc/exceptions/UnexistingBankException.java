package com.ftn.uns.pcc.exceptions;

public class UnexistingBankException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnexistingBankException(long id){
		super("Could not find bank with id "+id);
	}

}
