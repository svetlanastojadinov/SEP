package com.ftn.uns.payment_concentrator.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.service.OrderService;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Order>> getOrders() {
		
		ArrayList<Order> orders = (ArrayList<Order>) orderService.findAll();
		if (orders != null) {
			return new ResponseEntity<Collection<Order>>(orders, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<Order> getOrderById(@PathVariable long id) {
		Order order = orderService.findOne(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

}
