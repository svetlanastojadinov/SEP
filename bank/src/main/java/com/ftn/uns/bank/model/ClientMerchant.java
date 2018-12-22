package com.ftn.uns.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class ClientMerchant {

	@Column
	@Id
	private String merchantId;

	@Column
	private String merchantPassword;

	@Column
	private String username;

	@OneToOne
	private ClientAccount clientAccount;

	public ClientMerchant() {
	}

	public ClientMerchant(String merchantId, String merchantPassword, String username, ClientAccount clientAccountO) {
		super();
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.username = username;
		this.clientAccount = clientAccountO;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ClientAccount getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(ClientAccount clientAccount) {
		this.clientAccount = clientAccount;
	}

}
