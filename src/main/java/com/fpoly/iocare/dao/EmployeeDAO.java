package com.fpoly.iocare.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.iocare.model.Employee;
import com.fpoly.iocare.model.Semester;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, String>{
	@Query("Select Distinct ar.employee From Authority ar where ar.role.roleId IN ('DIR','STA')")
	List<Employee> getAdministrators();
}
