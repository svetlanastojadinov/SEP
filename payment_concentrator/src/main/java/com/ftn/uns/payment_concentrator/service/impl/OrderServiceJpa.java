package com.ftn.uns.payment_concentrator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_concentrator.exeptions.UnexistingOrderException;
import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.repository.OrderRepository;
import com.ftn.uns.payment_concentrator.service.OrderService;

@Service
public class OrderServiceJpa implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order findOne(long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new UnexistingOrderException(id));

		return order;
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public Order save(Order order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	@Override
	public void delete(Order order) {
		// TODO Auto-generated method stub
		orderRepository.delete(order);
	}

	@Override
	public Order updateExecution(Order order, boolean b) {
		order.setExecuted(b);
		return orderRepository.save(order);
	}

}
