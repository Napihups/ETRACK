package com.etrack.home.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.etrack.login.model.EUserDetails;
import com.etrack.security.JwtTokenUtility;

@RequestMapping("home")
@Controller
public class HomeController {

	private static final String TOKEN_HEADER = "Authorization";
	@Autowired
    private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private JwtTokenUtility jwtTokenUtil;
	
	@RequestMapping("/dashboard")
	@PreAuthorize("hasAnyRole('USER')")
	public String welcome(Model model, HttpServletRequest request){
		 String token = request.getHeader(TOKEN_HEADER);
       String username = jwtTokenUtil.getUsernameFromToken(token);
       EUserDetails user = (EUserDetails) userDetailsService.loadUserByUsername(username);
		model.addAttribute("message" , "Welcome to JSP spring boot");
		model.addAttribute("welcomename", user.getUserName());
		return "welcome";
	}
}
