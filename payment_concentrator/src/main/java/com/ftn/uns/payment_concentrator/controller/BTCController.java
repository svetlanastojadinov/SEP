package com.ftn.uns.payment_concentrator.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.dto.RemoveCartAfterPayingDTO;
import com.ftn.uns.payment_concentrator.model.Article;
import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.model.User;
import com.ftn.uns.payment_concentrator.paymentImpls.BTCClient;
import com.ftn.uns.payment_concentrator.service.ArticleService;
import com.ftn.uns.payment_concentrator.service.MagazineService;
import com.ftn.uns.payment_concentrator.service.UserService;

@RestController
@RequestMapping(value = "api/bitcoin")
public class BTCController {

	private final BTCClient bTCClient;
	private String currentIndicator = "";
	private String currentIdentificator = "";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private ArticleService articleService;

	@Autowired
	BTCController(BTCClient bTCClient) {
		this.bTCClient = bTCClient;
	}

	@RequestMapping(value = "/make/payment", method = RequestMethod.POST)
	public Map<String, Object> createOrder(@RequestBody Order order) {
		if(order.getArticle() != null) {
			currentIndicator = "article";
			currentIdentificator = Long.toString(order.getArticle().getId());
		}
		if(order.getMagazine() != null) {
			currentIndicator = "magazine";
			currentIdentificator = order.getMagazine().getIssn();
		}
		
		Map<String, Object> retVal = bTCClient.create(order);
		return retVal;
	}

	@RequestMapping(value = "/complete/payment", method = RequestMethod.POST)
	public ResponseEntity<?> complete(HttpServletRequest request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(username);
		bTCClient.complete(request);
		RemoveCartAfterPayingDTO bitcoinResponse= new RemoveCartAfterPayingDTO(currentIndicator,currentIdentificator);
		
		if(currentIndicator.equals("article")) {
			Article article = articleService.findOne(Long.valueOf(currentIdentificator));
			for(Article a : user.getArticles_in_cart()) {
				if(a.getId() == Long.valueOf(currentIdentificator)) {
					user.getArticles_in_cart().remove(a);
					article.getUser_cart().remove(user);
					articleService.save(article);
					userService.save(user);
				}
			}
		}
		
		if(currentIndicator.equals("magazine")) {
			Magazine magazine = magazineService.findOne(currentIdentificator);
			for(Magazine m : user.getMagazines_in_cart()) {
				user.getMagazines_in_cart().remove(m);
				magazine.getUser_cart().remove(user);
				magazineService.save(magazine);
				userService.save(user);		
			}
		}
		return new ResponseEntity<>(bitcoinResponse,HttpStatus.OK);
	}

}
