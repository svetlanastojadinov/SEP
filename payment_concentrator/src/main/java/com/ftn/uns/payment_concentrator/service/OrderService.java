package com.ftn.uns.payment_concentrator.service;

import java.util.List;

import com.ftn.uns.payment_concentrator.model.Order;

public interface OrderService {
	
	public Order findOne(long id);

	public List<Order> findAll();
	
	public List<Order> findAllByBuyer(String buyerUsername);

	public Order save(Order order);

	public void delete(Order order);

	public Order updateExecution(Order order, boolean b);
	
	public Order findByToken(String token);

}
