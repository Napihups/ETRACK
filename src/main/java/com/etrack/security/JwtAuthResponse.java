package com.etrack.security;

import java.io.Serializable;

public class JwtAuthResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3573503305053513400L;

	private final String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
	
}
