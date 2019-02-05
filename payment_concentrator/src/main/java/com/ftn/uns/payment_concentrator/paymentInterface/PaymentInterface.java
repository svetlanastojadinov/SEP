package com.ftn.uns.payment_concentrator.paymentInterface;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.model.Order;

public interface PaymentInterface {
	public Map<String, Object> create(Order order);

	public Map<String, Object> complete(HttpServletRequest request);
	
	public Map<String, Object> createMembershipPaying(Magazine magazine);
}
