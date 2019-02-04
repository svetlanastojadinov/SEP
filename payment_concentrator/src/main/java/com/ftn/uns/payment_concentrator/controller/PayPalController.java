package com.ftn.uns.payment_concentrator.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.dto.RemoveCartAfterPayingDTO;
import com.ftn.uns.payment_concentrator.model.Article;
import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.model.User;
import com.ftn.uns.payment_concentrator.paymentImpls.PayPalClient;
import com.ftn.uns.payment_concentrator.service.ArticleService;
import com.ftn.uns.payment_concentrator.service.MagazineService;
import com.ftn.uns.payment_concentrator.service.UserService;

@RestController
@RequestMapping(value = "api/paypal")
public class PayPalController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private ArticleService articleService;


	private final PayPalClient payPalClient;
	
	private String currentIndicator;
	private String currentIdentificator;

	@Autowired
	PayPalController(PayPalClient payPalClient) {
		this.payPalClient = payPalClient;
	}

	@PostMapping(value = "/make/payment")
	public Map<String, Object> makePayment(@RequestBody Order order) {
		if(order.getArticle() != null) {
			currentIndicator = "article";
			currentIdentificator = Long.toString(order.getArticle().getId());
		}
		if(order.getMagazine() != null) {
			currentIndicator = "magazine";
			currentIdentificator = order.getMagazine().getIssn();
		}
		return payPalClient.create(order);
	}

	@PostMapping(value = "/complete/payment")
	public ResponseEntity<?> completePayment(HttpServletRequest request) {
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		payPalClient.complete(request);
		RemoveCartAfterPayingDTO paypalResponse= new RemoveCartAfterPayingDTO(currentIndicator,currentIdentificator);
		
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
		
		return new ResponseEntity<>(paypalResponse,HttpStatus.OK);		
	}
}
