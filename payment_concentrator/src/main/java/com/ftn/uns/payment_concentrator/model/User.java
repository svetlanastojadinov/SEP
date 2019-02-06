package com.ftn.uns.payment_concentrator.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ftn.uns.payment_concentrator.model.role.Role;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public User(){
		this.articles = new HashSet<Article>(0);
		this.magazines = new HashSet<Magazine>(0);
	}
	
	public User(String username, String password, String email, String name, String surname, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.articles = new HashSet<Article>(0);
		this.magazines = new HashSet<Magazine>(0);
	}
	
	public User(Long id, String username, String password, String email, String name, String surname, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.articles = new HashSet<Article>(0);
		this.magazines = new HashSet<Magazine>(0);
	}
	public User(Long id, String username, String password, String email, String name, String surname, Role role,
			Set<Article> articles, Set<Magazine> magazines) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.articles = articles;
		this.magazines = magazines;
	}


	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	@Column
	private String password;

	@Column(unique = true)
	private String email;

	@Column
	private String name;

	@Column
	private String surname;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	
	//created
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Article> articles = new HashSet<Article>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Magazine> magazines = new HashSet<Magazine>(0);
	
	//in cart
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	    @JoinTable(name = "user_article_in_cart",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "articles_id"))
	private Set<Article> articles_in_cart = new HashSet<Article>(0);
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "user_magazine_in_cart",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "magazine_id"))
	private Set<Magazine> magazines_in_cart = new HashSet<Magazine>(0);
	

	public Set<Article> getArticles_in_cart() {
		return articles_in_cart;
	}

	public void setArticles_in_cart(Set<Article> articles_in_cart) {
		this.articles_in_cart = articles_in_cart;
	}

	public Set<Magazine> getMagazines_in_cart() {
		return magazines_in_cart;
	}

	public void setMagazines_in_cart(Set<Magazine> magazines_in_cart) {
		this.magazines_in_cart = magazines_in_cart;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Set<Magazine> getMagazines() {
		return magazines;
	}

	public void setMagazines(Set<Magazine> magazines) {
		this.magazines = magazines;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
