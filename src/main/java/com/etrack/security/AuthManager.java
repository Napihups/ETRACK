package com.etrack.security;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import com.etrack.login.model.EUserDetails;

@Component
public class AuthManager implements AuthenticationManager {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getPrincipal() + "";
		String password = auth.getCredentials() + "";
		EUserDetails user = (EUserDetails)userDetailsService.loadUserByUsername(username);
		if(user == null){
			System.out.println("Bad Credentials");
		}
		if(matchPass(password, user.getPassword())){
			return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
		} else {
			return null;
		}

	}
	


	// FOR TESTING ONLY 
	public Boolean matchPass(String rawPass, String dbPass) {
		if(rawPass.equals(dbPass)){
			return true;
		}else {
			return false;
		}
	}
	

	/**
	 * To matches the password
	 * 
	 * @param rawPassword
	 * @param encodedPassword
	 * @return
	 */
	public boolean matches(String rawPassword, String encodedPassword) {
		String encryptPass = BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));
		if(BCrypt.checkpw(encodedPassword, encryptPass)){
			return true;
		} else {
			return false;
		}
	}

}
