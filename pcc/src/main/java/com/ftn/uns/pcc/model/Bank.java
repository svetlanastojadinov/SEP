package com.ftn.uns.pcc.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Bank {

	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private long bin;

	@Column
	private int port;

	@Column
	private String url;

	@Column
	private String name;

	@Column
	private String brand;

	@Column
	private String host;

	public Bank() {
	}

	public Bank(long id, long bin, int port, String name, String brand, String url) {
		super();
		this.id = id;
		this.bin = bin;
		this.port = port;
		this.name = name;
		this.brand = brand;
		this.url = url;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBin() {
		return bin;
	}

	public void setBin(long bin) {
		this.bin = bin;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSite() {
		return host;
	}

	public void setSite(String site) {
		this.host = site;
	}

}
