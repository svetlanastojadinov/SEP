package com.ftn.uns.payment_concentrator.model;

public class Article {
	private String title;
	private String authorUsername;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(String title, String authorUsername) {
		super();
		this.title = title;
		this.authorUsername = authorUsername;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

}
