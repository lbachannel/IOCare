package com.fpoly.iocare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpoly.iocare.dao.IAuthorityDAO;
import com.fpoly.iocare.dao.IEmployeeDAO;
import com.fpoly.iocare.dao.ISemesterDAO;
import com.fpoly.iocare.model.Authority;
import com.fpoly.iocare.model.Employee;
import com.fpoly.iocare.model.Semester;
import com.fpoly.iocare.service.IAuthorityService;
import com.fpoly.iocare.service.IEmployeeService;


@Service
public class AuthorityServiceImpl implements IAuthorityService{
	
	@Autowired
	IAuthorityDAO dao;

	@Autowired
	BCryptPasswordEncoder pe;
	@Override
	public Authority create(Authority authority) {
		
		return dao.save(authority);
	}
}
