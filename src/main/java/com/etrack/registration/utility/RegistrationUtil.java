package com.etrack.registration.utility;

import java.util.HashSet;
import java.util.Set;

import com.etrack.registration.model.UserRole;
import com.etrack.registration.model.Users;

public class RegistrationUtil {
	
	private static final int ACC_TYPE_1 = 1;
	private static final int ACC_TYPE_2 = 2;
	private static final String ADMIN = "ROLE_ADMIN";
	private static final String USER = "ROLE_USER";
	
	private RegistrationUtil(){
		// no args const
	}
	
	/**
	 * 
	 * @param user
	 */
	public static void setupUserRights(Users user){
		if(ACC_TYPE_1 == user.getAccType()){
			setRightForAccType1(user);
		} else if (ACC_TYPE_2 == user.getAccType()){
			setRightForAccType2(user);
		}
	}


	/**
	 * 
	 * @param user
	 */
	private static void setRightForAccType2(Users user) {
		Set<UserRole> userRights = new HashSet<UserRole>();
		userRights.add(new UserRole(USER));
		user.setUserRights(userRights);
	}


	/**
	 * 
	 * @param user
	 */
	private static void setRightForAccType1(Users user) {
		Set<UserRole> userRights = new HashSet<UserRole>();
		userRights.add(new UserRole(ADMIN));
		userRights.add(new UserRole(USER));
		user.setUserRights(userRights);
	}
}
