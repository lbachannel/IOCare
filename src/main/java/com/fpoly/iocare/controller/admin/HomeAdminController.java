package com.fpoly.iocare.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeAdminController {
	@RequestMapping("")
	public String index(){
		return "admin/index";
	}
	
	/*--url: Quản lý chiến dịch--*/
	@RequestMapping("/campaign-management")
	public String campaignManagement(){
		return "admin/index";
	}
	
	/*--url: Chăm sóc sinh viên--*/
	@RequestMapping("/student-support")
	public String studentSupport() {
		return "admin/index";
	}
	
	/*--url: Quản lý tài khoản--*/
	@RequestMapping("/account-management")
	public String accountManagement() {
		return "admin/index";
	}
	
	/*--url: Quản lý data--*/
	@RequestMapping("/data-management")
	public String dataManagement() {
		return "admin/index";
	}
}
