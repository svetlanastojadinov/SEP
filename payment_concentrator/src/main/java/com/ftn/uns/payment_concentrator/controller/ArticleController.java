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

import com.ftn.uns.payment_concentrator.model.Article;
import com.ftn.uns.payment_concentrator.service.ArticleService;

@RestController
@RequestMapping(value = "/api/articles")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Article>> getArticles() {
		
		ArrayList<Article> articles = (ArrayList<Article>) articleService.findAll();
		if (articles != null) {
			return new ResponseEntity<Collection<Article>>(articles, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<Article> getArticleById(@PathVariable long id) {
		Article article = articleService.findOne(id);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
}
