package com.example.quiz_1141013.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "account")
	private String account;

	@Column(name = "password")
	private String password;

	public User() {
		super();
	}

	public User(String email, String account, String password) {
		super();
		this.email = email;
		this.account = account;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
