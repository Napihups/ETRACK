package com.etrack.registration.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.etrack.registration.model.Users;
import com.etrack.registration.service.RegistrationService;

@RestController
@RequestMapping("account") 
public class RegistrationRest {
	
	@Autowired
	RegistrationService accService;
	
	
	/*-----------------------------------
	 * API END POINT FOR ACCOUNT 
	 ------------------------------------*/
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@RequestBody Users user){
		if(accService.doRegister(user) == 1){
			return "Succefully registered";
		} else {
			return "Failed to register";
		}
	}
	
	@RequestMapping("/test")
	public String test(){
		return "/home";
	}
	
	
	
	
}
