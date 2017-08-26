package com.etrack.registration.model;

public class UserRole {

	private String right;

	public UserRole() {
		// no args const
	}

	public UserRole(String right) {
		super();
		this.right = right;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

}
