package com.etrack.login.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.etrack.login.model.AuthLoginRequest;
import com.etrack.login.model.AuthUserResponse;
import com.etrack.login.model.EUserDetails;
import com.etrack.security.JwtTokenUtility;

@Controller
@CrossOrigin(origins="*")
public class LoginRest {
	
	private static final String TOKEN_HEADER = "Authorization";
	
	
	
	@Autowired
    private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private JwtTokenUtility jwtTokenUtil;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> doUserLogin(@RequestBody AuthLoginRequest loginRequest, Device device){

		String username = loginRequest.getUserName();
		String password = loginRequest.getPassword();
		// Perform the security
		try{
			final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(username, password)
	        );
	    	if(authentication == null){
	    		final AuthUserResponse authResp = new AuthUserResponse();
	   		 authResp.setSuccess(false);
	   		 authResp.setMsg("Invalid Username or Password ");
	    	}
	    	if(authentication.isAuthenticated()){
	    		SecurityContextHolder.getContext().setAuthentication(authentication);
	       	 
		       	 //To generate the token 
		       	 final UserDetails userDetail = userDetailsService.loadUserByUsername(username);
		       	 final String token = jwtTokenUtil.generateToken(userDetail, device);
		       	 final AuthUserResponse authResp = new AuthUserResponse();
		       	 authResp.setToken(token);
		       	 authResp.setSuccess(true);
		       	 authResp.setMsg("Succesfully logged in ");
		       	 authResp.setUserName(userDetail.getUsername());
		       	 return ResponseEntity.ok(authResp);
	    	} else {
	    		 final AuthUserResponse authResp = new AuthUserResponse();
	    		 authResp.setSuccess(false);
	    		 authResp.setMsg("Invalid Username or Password ");
	    		return ResponseEntity.ok(authResp);
	    	}
		}catch(NullPointerException e){
			 final AuthUserResponse authResp = new AuthUserResponse();
    		 authResp.setSuccess(false);
    		 authResp.setMsg("Invalid Username or Password ");
    		return ResponseEntity.ok(authResp);
		}
    	
	}
	

	

	

	
	
	
	
	
	
	
//==================================================================================================//
//	@RequestMapping("/testjsp")
//	public String welcome(Model model) {
//		model.addAttribute("message", "Welcome to JSP Spring boot !!!");
//		return "welcome";
//	}
	
//	@RequestMapping(value = "admin", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> getProtectedGreeting() {
//        return ResponseEntity.ok("Greetings from admin protected method!");
//    }
//	
//	@RequestMapping(value = "normal", method = RequestMethod.GET)
//	@PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> getUser() {
//        return ResponseEntity.ok("User method!");
//    }
//	
//	@RequestMapping(value = "user", method = RequestMethod.GET)
//    public ResponseEntity<?> getAuthenticatedUser(HttpServletRequest request) {
//        String token = request.getHeader(TOKEN_HEADER);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        EUserDetails user = (EUserDetails) userDetailsService.loadUserByUsername(username);
//        return ResponseEntity.ok(user);
//    }

}
