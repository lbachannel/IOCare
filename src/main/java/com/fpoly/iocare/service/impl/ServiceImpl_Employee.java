package com.fpoly.iocare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.iocare.dao.EmployeeDAO;
import com.fpoly.iocare.model.Employee;
import com.fpoly.iocare.service.Service_Employee;

@Service
public class ServiceImpl_Employee implements Service_Employee{
	@Autowired private EmployeeDAO accDao;

	@Override
	public Employee findById(String username) {
		return accDao.findById(username).get();
	}

	@Override
	public List<Employee> getAdministrators() {
		return accDao.getAdministrators();
	}

	@Override
	public List<Employee> findAll() {
		return accDao.findAll();
	}

	@Override
	public Employee create(Employee Employee) {
		return accDao.save(Employee);
	}

	@Override
	public Employee update(Employee Employee) {
		return accDao.save(Employee);
	}
	/*Summary*/

	@Override
	public Long getTotalEmployee() {
		return accDao.count();
	}


	
}
