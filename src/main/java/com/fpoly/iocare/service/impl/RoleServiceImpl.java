package com.fpoly.iocare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpoly.iocare.dao.IAuthorityDAO;
import com.fpoly.iocare.dao.IEmployeeDAO;
import com.fpoly.iocare.dao.IRoleDAO;
import com.fpoly.iocare.dao.ISemesterDAO;
import com.fpoly.iocare.model.Authority;
import com.fpoly.iocare.model.Employee;
import com.fpoly.iocare.model.Semester;
import com.fpoly.iocare.service.IAuthorityService;
import com.fpoly.iocare.service.IEmployeeService;
import com.fpoly.iocare.service.IRoleService;


@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	IRoleDAO dao;

	@Autowired
	BCryptPasswordEncoder pe;
}
