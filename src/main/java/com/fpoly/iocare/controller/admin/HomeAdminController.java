package com.fpoly.iocare.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeAdminController {
	@RequestMapping("")
	public String index(Model model){
		return "admin/sign-in";
	}
}
