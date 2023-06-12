package com.fpoly.iocare.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fpoly.iocare.model.Employee;


@Service
public interface Service_Employee {
	Employee findById(String username);
	
	Employee findByEmail(String email);

	List<Employee> getAdministrators();

	List<Employee> findAll();

	Employee create(Employee Employee);

	Employee update(Employee Employee);

	Long getTotalEmployee();

}
