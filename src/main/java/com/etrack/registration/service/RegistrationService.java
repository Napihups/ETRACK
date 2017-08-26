package com.etrack.registration.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrack.registration.exception.RegisterException;
import com.etrack.registration.model.UserRole;
import com.etrack.registration.model.Users;
import com.etrack.registration.repository.UserRepository;
import com.etrack.registration.utility.RegistrationUtil;

@Service
public class RegistrationService {
	
	
	
	@Autowired
	UserRepository userPers;
	
	/*---------------------------------
	 * SERVICES METHODS
	 ---------------------------------*/
	public int doRegister(Users user){
		try {
			// make sure to runs code below only once , upon registration
			user.setLastPasswordReset(new Date());
			RegistrationUtil.setupUserRights(user);
			userPers.registerNewUser(user);
			return 1;
		} catch (RegisterException e) {
			System.out.println("Register Exception error :" + e);
			return 0;
		} catch (SQLException e) {
			System.out.println("SQL exception error : " + e);
			return 0;
		}
	}
	
	
	
	
	
	
	
	
	
}
