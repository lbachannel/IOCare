package com.fpoly.iocare.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.iocare.model.Employee;

@Repository
public interface IEmployeeDAO extends JpaRepository<Employee, String>{
	Optional<Employee> findById(String employeeId);
}
