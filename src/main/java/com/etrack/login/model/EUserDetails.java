package com.etrack.login.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.etrack.registration.model.Users;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EUserDetails extends Users implements UserDetails {

	@JsonProperty("AUTHORITIES")
	 private Collection<? extends GrantedAuthority> authorities;
	 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2679148058756941285L;

	public EUserDetails(Users user){
		super(user);
		this.authorities = getAuthorities();
	}
	

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return  super.getUserRights().stream()
                .map(right -> new SimpleGrantedAuthority(right.getRight()))
                .collect(Collectors.toList());
	}


	@Override
	public String getPassword() {
		return super.getPassWord();
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getUserEmailAddress(){
		return super.getEmail();
	}

}
