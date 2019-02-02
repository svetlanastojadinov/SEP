package com.ftn.uns.payment_concentrator.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_concentrator.dto.CartDTO;
import com.ftn.uns.payment_concentrator.model.Article;
import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.model.User;
import com.ftn.uns.payment_concentrator.service.ArticleService;
import com.ftn.uns.payment_concentrator.service.MagazineService;
import com.ftn.uns.payment_concentrator.service.UserService;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCart", method=RequestMethod.GET)
	public ResponseEntity<CartDTO> getCartContent() {
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Set<Article> articles = user.getArticles();
		Set<Magazine> magazines =user.getMagazines();

		CartDTO cart = new CartDTO();
		cart.setArticles(articles);
		cart.setMagazines(magazines);
		System.out.println();
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addArticleToCart/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> addArticleToCart(@PathVariable Long id) {
		Article article = articleService.findOne(id);
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.getArticles().add(article);
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addMagazineToCart/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> addMagazineToCart(@PathVariable String id) {
		Magazine magazine = magazineService.findOne(id);
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.getMagazines().add(magazine);
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}