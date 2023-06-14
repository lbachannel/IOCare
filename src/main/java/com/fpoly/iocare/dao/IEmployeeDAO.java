package com.fpoly.iocare.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fpoly.iocare.model.Employee;

@Repository
public interface IEmployeeDAO extends JpaRepository<Employee, String>{
	@Query(value = "select * from Employees where employeeEmail = ?1", nativeQuery = true)
	boolean findByEmail(String email);
	
	@Query("select distinct ar.employee from Authority ar where ar.role.id in ('1', '2', '3')")
	List<Employee> getAdministrators();
}