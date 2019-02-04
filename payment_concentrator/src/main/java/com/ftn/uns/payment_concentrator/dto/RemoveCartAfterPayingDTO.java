package com.ftn.uns.payment_concentrator.dto;


public class RemoveCartAfterPayingDTO {

	private String currentIndicator;
	private String currentIdentificator;
	
	public RemoveCartAfterPayingDTO(){}

	public RemoveCartAfterPayingDTO(String currentIndicator,String currentIdentificator) {
		super();
		this.currentIndicator = currentIndicator;
		this.currentIdentificator = currentIdentificator;
	}


	public String getCurrentIndicator() {
		return currentIndicator;
	}

	public void setCurrentIndicator(String currentIndicator) {
		this.currentIndicator = currentIndicator;
	}

	public String getCurrentIdentificator() {
		return currentIdentificator;
	}

	public void setCurrentIdentificator(String currentIdentificator) {
		this.currentIdentificator = currentIdentificator;
	}
	
}
