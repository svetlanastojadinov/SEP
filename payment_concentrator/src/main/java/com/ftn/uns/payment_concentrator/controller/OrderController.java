package com.ftn.uns.payment_concentrator.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.model.PaymentType;
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

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<Order> saveOrder(@Valid @RequestBody Order order) {
		Order savedOrder = orderService.save(order);
		return new ResponseEntity<Order>(savedOrder, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	private ResponseEntity<Order> updateOrder(@PathVariable Long id, @Valid @RequestBody Order order) {

		Order updatedOrder = orderService.findOne(id);

		if (order.getPrice() != updatedOrder.getPrice()) {
			updatedOrder.setPrice(order.getPrice());
		}
		if (order.getPaymentType() != updatedOrder.getPaymentType()) {
			updatedOrder.setPaymentType(order.getPaymentType());
		}
		if (order.getPayerUsername() != updatedOrder.getPayerUsername()) {
			updatedOrder.setPayerUsername(order.getPayerUsername());
		}
		if (order.getMagazine() != updatedOrder.getMagazine()) {
			updatedOrder.setMagazine(order.getMagazine());
		}
		if (order.getDate() != updatedOrder.getDate()) {
			updatedOrder.setDate(order.getDate());
		}
		if (order.getArticle() != updatedOrder.getArticle()) {
			updatedOrder.setArticle(order.getArticle());
		}
		if (order.getExecuted() != updatedOrder.getExecuted()) {
			updatedOrder.setExecuted(order.getExecuted());
		}

		orderService.update(updatedOrder, id);
		return new ResponseEntity<Order>(updatedOrder, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
		orderService.delete(orderService.findOne(id));
		return new ResponseEntity<Order>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "buy", method = RequestMethod.POST)
	private ResponseEntity<Order> buy(@RequestBody Order order) {
		/*System.out.println(order.getPrice());
		System.out.println(order.getPayerUsername());
		System.out.println(order.getPaymentType());
		System.out.println(order.getMagazine());
		System.out.println(order.getArticle());*/
		if(order.getPaymentType() == PaymentType.CARD) {
			System.out.println("Cepanje kartice");
		}
		
		if(order.getPaymentType() == PaymentType.PAYPAL) {
			System.out.println("Udri paypal");
		}
		
		if(order.getPaymentType() == PaymentType.BITCOIN) {
			System.out.println("Daj mu ga malo po bitcoin");
		}
		return null;
	}
}
