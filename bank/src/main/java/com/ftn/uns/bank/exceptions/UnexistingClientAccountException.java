package com.ftn.uns.bank.exceptions;

public class UnexistingClientAccountException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnexistingClientAccountException(String pan){
		super("Could not find client account with pan: "+pan);
	}

}
