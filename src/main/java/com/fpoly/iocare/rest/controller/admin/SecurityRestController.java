package com.fpoly.iocare.rest.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;

@CrossOrigin("*")
@RestController
public class SecurityRestController {
	
	@GetMapping("security")
	public Boolean campaign_Management(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 System.out.println(authentication.isAuthenticated());
        return authentication.isAuthenticated();
	}
}
