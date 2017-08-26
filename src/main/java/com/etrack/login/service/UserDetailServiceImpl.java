package com.etrack.login.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.etrack.login.model.EUserDetails;
import com.etrack.login.repository.LoginRepository;
import com.etrack.registration.model.Users;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user;
		try {
			user = loginRepo.findUserByUsername(username);
			return new EUserDetails(user);
		} catch (UsernameNotFoundException e) {
			// will think of how to manage my exception handling
			throw new UsernameNotFoundException("User has unknown roles , please register ");
		} catch (SQLException e){
			// will think of how to manage my exception handling
			System.out.println(e);
			throw new UsernameNotFoundException("User has unknown roles , please register ");
		}
		
	}

}
