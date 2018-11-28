package com.ftn.uns.payment_concentrator.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Magazine {

	@Column
	private String title;

	@Id
	@Column(nullable = false, unique = true)
	private String issn;

	@Column
	private Subscription subscription;

	@OneToMany
	private Collection<Article> articles;

	public Magazine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Magazine(String title, String issn, Subscription subscription, Collection<Article> articles) {
		super();
		this.title = title;
		this.issn = issn;
		this.subscription = subscription;
		// this.articles = articles;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	/*
	 * public Collection<Article> getArticles() { return articles; }
	 * 
	 * public void setArticles(Collection<Article> articles) { this.articles =
	 * articles; }
	 */

}
