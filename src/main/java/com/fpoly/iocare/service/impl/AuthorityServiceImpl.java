package com.fpoly.iocare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpoly.iocare.dao.IAuthorityDAO;
import com.fpoly.iocare.dao.IEmployeeDAO;
import com.fpoly.iocare.model.Authority;
import com.fpoly.iocare.model.Employee;
import com.fpoly.iocare.service.IAuthorityService;


@Service
public class AuthorityServiceImpl implements IAuthorityService{
	
	@Autowired
	IAuthorityDAO dao;

	@Autowired
	IEmployeeDAO iEmployeeDAO;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Override
	public List<Authority> findAll(){
		return dao.findAll();
	}
	
	@Override
	public Authority create(Authority authority) {
		return dao.save(authority);
	}
	
	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Employee> employees = iEmployeeDAO.getAdministrators();
		return dao.authoritiesOf(employees);
	}
	
}
