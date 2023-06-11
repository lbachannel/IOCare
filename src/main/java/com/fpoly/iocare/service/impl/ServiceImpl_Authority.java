package com.fpoly.iocare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.iocare.dao.AuthorityDAO;
import com.fpoly.iocare.dao.EmployeeDAO;
import com.fpoly.iocare.model.Authority;
import com.fpoly.iocare.model.Employee;
import com.fpoly.iocare.service.Service_Authority;

@Service
public class ServiceImpl_Authority implements Service_Authority{
	
	@Autowired private AuthorityDAO authdao;
	@Autowired private EmployeeDAO accdao;
	
	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Employee> Employees = accdao.getAdministrators();
		return authdao.authoritiesOf(Employees);
	}

	@Override
	public List<Authority> findAll() {
		return authdao.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		return authdao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		authdao.deleteById(id);
	}

	@Override
	public List<Authority> getOneByRole(String username) {
		return authdao.getOneByRole(username);
	}

	@Override
	public void deleteById(String username) {
		authdao.deleteById(username);
	}

	@Override
	public Long getTotalCustomer() {
		return authdao.findAll().stream().filter(e->e.getRole().getRoleName().equals("Customers")).count();
	}
	
}
