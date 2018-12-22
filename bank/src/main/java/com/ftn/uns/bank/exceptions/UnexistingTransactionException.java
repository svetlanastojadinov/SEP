package com.ftn.uns.bank.exceptions;

public class UnexistingTransactionException extends RuntimeException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnexistingTransactionException(long id){
		super("Could not find transaction with id: "+id);
	}
}
