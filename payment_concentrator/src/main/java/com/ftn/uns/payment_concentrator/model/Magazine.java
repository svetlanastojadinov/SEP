package com.ftn.uns.payment_concentrator.model;

import java.util.Collection;

public class Magazine {
	private String title;
	private int issn;
	private Subscription subscription;
	private Collection<Article> articles;

	public Magazine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Magazine(String title, int issn, Subscription subscription, Collection<Article> articles) {
		super();
		this.title = title;
		this.issn = issn;
		this.subscription = subscription;
		this.articles = articles;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIssn() {
		return issn;
	}

	public void setIssn(int issn) {
		this.issn = issn;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public Collection<Article> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}

}
