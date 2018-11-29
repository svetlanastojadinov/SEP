package com.ftn.uns.payment_concentrator.service;

import java.util.List;

import com.ftn.uns.payment_concentrator.model.Article;

public interface ArticleService {

	public Article findOne(long id);

	public List<Article> findAll();

	public Article save(Article article);

	public void delete(Article article);

	public Article update(Article article, long id);

}
