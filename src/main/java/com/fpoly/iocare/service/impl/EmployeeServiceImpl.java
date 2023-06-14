package com.fpoly.iocare.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpoly.iocare.dao.IEmployeeDAO;
import com.fpoly.iocare.model.Employee;
import com.fpoly.iocare.service.IEmployeeService;


@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	IEmployeeDAO dao;

	@Autowired
	BCryptPasswordEncoder pe;
	@Override
	public Employee create(Employee employee) {
		return dao.save(employee);
	}
	@Override
	public boolean existsById(String id) {
		if(dao.existsById(id)) 
			return true;
		return false;
	}
	
	@Override
	public boolean findByEmail(String email) {
		if(dao.findByEmail(email))
			return true;
		return false;
	}
	
	@Override
	public List<Employee> findAll() {
		return dao.findAll();
	}
	
	@Override
	public List<Employee> getAdministrators() {
		return dao.getAdministrators();
	}
	
//	@Override
//	public Employee resetPassword(String email) {
//		Employee existAccount = dao.findByEmail(email);
//		if(existAccount != null) {
//			// random 4 số | 1000 - 9999 | công thức: (Math.random()) * ((max - min) + 1)) + min
//			String newPass = String.valueOf((int)(Math.random() * ((9999 - 1000) + 1)) + 1000);
//			existAccount.setEmployeePassword(newPass); // set password cho user
//			return dao.save(existAccount); // gọi xuống dao
//		}
//		return null;
//	}
}