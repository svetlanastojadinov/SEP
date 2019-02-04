package com.ftn.uns.payment_concentrator.controller;

import java.util.List;

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
	public ResponseEntity<?> getCartContent() {
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		CartDTO cart = new CartDTO(user.getArticles_in_cart(), user.getMagazines_in_cart());
		System.out.println();
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addArticleToCart/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> addArticleToCart(@PathVariable Long id) {
		Article article = articleService.findOne(id);
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		for(Article a : user.getArticles_in_cart()) {
			if(a.getId() == id) {
				return new ResponseEntity<>("ARTICLE_EXISTS", HttpStatus.OK);
			}
		}
		user.getArticles_in_cart().add(article);
		article.getUser_cart().add(user);
		articleService.save(article);
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addMagazineToCart/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> addMagazineToCart(@PathVariable String id) {
		Magazine magazine = magazineService.findOne(id);
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		for(Magazine m : user.getMagazines_in_cart()) {
			if(m.getIssn().equals(id)) {
				return new ResponseEntity<>("MAGAZINE_EXISTS", HttpStatus.OK);
			}
		}
		user.getMagazines_in_cart().add(magazine);
		magazine.getUser_cart().add(user);
		magazineService.save(magazine);
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/removeArticleFromCart/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> removeArticleFromCart(@PathVariable Long id) {
		Article article = articleService.findOne(id);
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		for(Article a : user.getArticles_in_cart()) {
			if(a.getId() == id) {
				user.getArticles_in_cart().remove(a);
				article.getUser_cart().remove(user);
				articleService.save(article);
				userService.save(user);
				return new ResponseEntity<>(article.getId(), HttpStatus.OK);
			}
		}
	
		return new ResponseEntity<>("NOT_EXISTS", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/removeMagazineFromCart/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> removeMagazineFromCart(@PathVariable String id) {
		Magazine magazine = magazineService.findOne(id);
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		for(Magazine m : user.getMagazines_in_cart()) {
			if(m.getIssn().equals(id)) {
				user.getMagazines_in_cart().remove(m);
				magazine.getUser_cart().remove(user);
				magazineService.save(magazine);
				userService.save(user);
				return new ResponseEntity<>(magazine.getIssn(), HttpStatus.OK);
			}
		}
	
		return new ResponseEntity<>("NOT_EXISTS", HttpStatus.BAD_REQUEST);	
	}
	
	
	
}
