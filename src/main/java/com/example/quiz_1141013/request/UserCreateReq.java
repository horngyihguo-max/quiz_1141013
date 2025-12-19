package com.example.quiz_1141013.request;

import com.example.quiz_1141013.constants.UserMsg;

import jakarta.validation.constraints.NotBlank;

public class UserCreateReq {

	@NotBlank(message = UserMsg.EMAIL_ERROR)
	private String email;

	@NotBlank(message = UserMsg.ACCOUNT_ERROR)
	private String account;

	@NotBlank(message = UserMsg.PASSWORD_ERROR)
	private String password;

	public UserCreateReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserCreateReq(@NotBlank(message = "Email error!!") String email,
			@NotBlank(message = "Account error!!") String account,
			@NotBlank(message = "Password error!!") String password) {
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
