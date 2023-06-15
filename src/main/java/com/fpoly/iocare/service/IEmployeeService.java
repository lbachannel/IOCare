package com.fpoly.iocare.service;

import java.util.List;

import com.fpoly.iocare.model.Employee;

public interface IEmployeeService {
	/*--Thêm mới nhân sự--*/
	Employee create(Employee employee);
	
	/*--Kiểm tra mã nhân sự có tồn tại hay chưa--*/
	boolean existsById(String id);

	boolean findByEmail(String email);

	List<Employee> getAdministrators();
	
	/*--Hiển thị tất cả nhân sự--*/
	List<Employee> findAll();
	
//	Employee resetPassword(String email);
}