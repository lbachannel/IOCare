package com.fpoly.iocare.service;

import java.util.List;

import com.fpoly.iocare.model.Employee;

public interface IEmployeeService {
	
	Employee create(Employee employee);
	
	boolean existsById(String id);

	boolean findByEmail(String email);

	List<Employee> getAdministrators();
	
	List<Employee> findAll();
	
//	Employee resetPassword(String email);
}