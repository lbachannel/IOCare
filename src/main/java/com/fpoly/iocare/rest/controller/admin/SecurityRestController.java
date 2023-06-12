package com.fpoly.iocare.rest.controller.admin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class SecurityRestController {
	
	@GetMapping("campaign-management")
	public String campaign_Management(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    
		    if (authentication.isAuthenticated()) {
		        String username = authentication.getName();
		       
		        System.out.println(username);
		        return username;
		    } else {
		        return null;
		    }
	}
	
	@GetMapping("student-support")
	public String student_support(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    
		    if (authentication.isAuthenticated()) {
		        String username = authentication.getName();
		       
		        System.out.println(username);
		        return username;
		    } else {
		        return null;
		    }
	}
	
	@GetMapping("account-management")
	public String account_management(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    
		    if (authentication.isAuthenticated()) {
		        String username = authentication.getName();
		       
		        System.out.println(username);
		        return username;
		    } else {
		        return null;
		    }
	}
	
	@GetMapping("authority-management")
	public String authority_management(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    
		    if (authentication.isAuthenticated()) {
		        String username = authentication.getName();
		       
		        System.out.println(username);
		        return username;
		    } else {
		        return null;
		    }
	}
}
