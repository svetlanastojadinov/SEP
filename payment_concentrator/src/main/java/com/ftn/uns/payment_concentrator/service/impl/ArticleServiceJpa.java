package com.ftn.uns.payment_concentrator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_concentrator.exeptions.UnexistingArticleException;
import com.ftn.uns.payment_concentrator.model.Article;
import com.ftn.uns.payment_concentrator.repository.ArticleRepository;
import com.ftn.uns.payment_concentrator.service.ArticleService;

@Service
public class ArticleServiceJpa implements ArticleService{
	
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public Article findOne(long id) {
		// TODO Auto-generated method stub
		Article article=articleRepository.findById(id).orElseThrow(()-> new UnexistingArticleException(id));
		return article;
	}

	@Override
	public List<Article> findAll() {
		// TODO Auto-generated method stub
		return articleRepository.findAll();
	}

	@Override
	public Article save(Article article) {
		// TODO Auto-generated method stub
		return articleRepository.save(article);
	}

	@Override
	public void delete(Article article) {
		// TODO Auto-generated method stub
		articleRepository.delete(article);
	}

	@Override
	public Article update(Article article, long id) {
		// TODO Auto-generated method stub
		Article articleToUpdate=this.findOne(id);
		articleToUpdate.setTitle(article.getTitle());
		articleToUpdate.setUser(article.getUser());
		
		return articleRepository.save(articleToUpdate);
	}

}
