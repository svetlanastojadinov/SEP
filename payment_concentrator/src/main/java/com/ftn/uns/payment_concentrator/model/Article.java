package com.ftn.uns.payment_concentrator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Article {

	@Id
	@Column
	private long id;
	
	@Column
	private String title;
	
	@Column
	private String authorUsername;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(long id, String title, String authorUsername) {
		super();
		this.id = id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
