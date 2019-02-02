package com.ftn.uns.payment_concentrator.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Magazine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String title;

	@Id
	@Column(nullable = false, unique = true)
	private String issn;

	@Column
	@Enumerated(EnumType.STRING)
	private Subscription subscription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "magazine_issn")
	private Set<Article> articles;

	private String author;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column
	private double price;

	public Magazine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Magazine(String title, String issn, Subscription subscription, User user, Set<Article> articles,
			String author, double price) {
		super();
		this.title = title;
		this.issn = issn;
		this.subscription = subscription;
		this.user = user;
		this.articles = articles;
		this.author = author;
		this.price = price;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Magazine [title=" + title + ", issn=" + issn + ", subscription=" + subscription + ", user=" + user
				+ ", author=" + author + ", price=" + price + "]";
	}

}
