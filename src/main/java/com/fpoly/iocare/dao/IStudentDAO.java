package com.fpoly.iocare.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.iocare.model.Student;

public interface IStudentDAO extends JpaRepository<Student, String>{
	/*--Lấy tất cả sinh viên--*/
	@Query(value = "select * from Students where ImportedFileName = ?1 AND Employeeid IS NULL", nativeQuery = true)
	List<Student> findAll(String importFileName);
	
	@Query(value = "select * from Students where Employeeid IS NOT NULL", nativeQuery = true)
	List<Student> findNotNull();
}
