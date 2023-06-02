package com.fpoly.iocare.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.iocare.dao.*;
import com.fpoly.iocare.model.RiskClassification;

@Controller
public class HomeAdminController {
	
	@Autowired
	RiskClassificationDAO dao;
	
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
	
	/*--url: Quản lý phân quyền--*/
	@RequestMapping("/authority-management")
	public String authorityManagement() {
		return "admin/index";
	}
}
