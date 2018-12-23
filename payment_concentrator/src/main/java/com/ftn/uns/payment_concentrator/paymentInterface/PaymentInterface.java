package com.ftn.uns.payment_concentrator.paymentInterface;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface PaymentInterface {
	public Map<String, Object> create(String sum);
	public Map<String, Object> complete(HttpServletRequest request);
}
