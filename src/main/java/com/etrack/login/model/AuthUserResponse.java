package com.etrack.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthUserResponse {

	@JsonProperty("TOKEN")
	private String token;
	@JsonProperty("SUCCESS")
	private boolean success;
	@JsonProperty("USERNAME")
	private String userName;
	@JsonProperty("MSG")
	private String msg;

	public AuthUserResponse() {
		// no args const
	}

	public AuthUserResponse(String token, boolean success, String userName, String msg) {
		super();
		this.token = token;
		this.success = success;
		this.userName = userName;
		this.msg = msg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
