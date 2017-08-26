package com.etrack.login.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("jsp")
@Controller
public class JSPController {
	
	@RequestMapping("/home")
	public String welcome(Model model){
		model.addAttribute("message" , "Welcome to JSP spring boot");
		return "welcome";
	}
	
}
