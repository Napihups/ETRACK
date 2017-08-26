package com.etrack.login.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthLoginRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5086448898704554870L;

	@JsonProperty("USERNAME")
	private String userName;
	@JsonProperty("PASSWORD")
	private String password;

	public AuthLoginRequest() {
		// no agrs const
	}

	public AuthLoginRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
