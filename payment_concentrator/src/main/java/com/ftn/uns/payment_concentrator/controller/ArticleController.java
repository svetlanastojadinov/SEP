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

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<Article> saveArticle(@Valid @RequestBody Article article) {

		Article savedArticle = articleService.save(article);

		return new ResponseEntity<Article>(savedArticle, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	private ResponseEntity<Article> updateArticle(@PathVariable long id, @Valid @RequestBody Article article) {

<<<<<<< HEAD
		Article updatedArticle = articleService.findOne(id);

		if (article.getUser().getUsername() != updatedArticle.getUser().getUsername()) {
			updatedArticle.setUser(article.getUser());
		}
		if (article.getTitle() != updatedArticle.getTitle()) {
			updatedArticle.setTitle(article.getTitle());
		}
		articleService.update(updatedArticle, id);
=======
		Article updatedArticle = articleService.update(article, id);
>>>>>>> e45c5b63e7a931d2990da712f7e3859e860d059c
		return new ResponseEntity<Article>(updatedArticle, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<Article> deleteArticle(@PathVariable long id) {

		articleService.delete(articleService.findOne(id));

		return new ResponseEntity<Article>(HttpStatus.OK);
	}
}
