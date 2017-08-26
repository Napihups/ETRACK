package com.etrack.registration.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {

	@JsonProperty("USER_ID")
	private int userId;
	@JsonProperty("ACTIVE")
	private int active;
	@JsonProperty("EMAIL")
	private String email;
	@JsonProperty("USERNAME")
	private String userName;
	@JsonProperty("PASSWORD")
	private String passWord;
	@JsonProperty("ACC_TYPE")
	private int accType;
	private Set<UserRole> userRights;
	private Date lastPasswordReset;
	private Boolean isForRegistration;

	public Users() {
		// no agrs const
	}

	// copy the users model to here
	public Users(Users user) {
		this.active = user.getActive();
		this.email = user.getEmail();
		this.accType = user.getAccType();
		this.userName = user.getUserName();
		this.passWord = user.getPassWord();
		this.userId = user.getUserId();
		this.lastPasswordReset = user.getLastPasswordReset();
		this.userRights = user.getUserRights();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getAccType() {
		return accType;
	}

	public void setAccType(int accType) {
		this.accType = accType;
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	public Set<UserRole> getUserRights() {
		return userRights;
	}

	public void setUserRights(Set<UserRole> userRights) {
		this.userRights = userRights;
	}

	public Boolean getIsForRegistration() {
		return isForRegistration;
	}

	public void setIsForRegistration(Boolean isForRegistration) {
		this.isForRegistration = isForRegistration;
	}

}
