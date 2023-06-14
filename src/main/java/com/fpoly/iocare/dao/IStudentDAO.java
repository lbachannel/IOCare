package com.fpoly.iocare.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.iocare.model.Student;

public interface IStudentDAO extends JpaRepository<Student, String>{
	@Query(value = "select * from Students where ImportedFileName = ?1", nativeQuery = true)
	List<Object[]> findAll(String importFileName);
}
