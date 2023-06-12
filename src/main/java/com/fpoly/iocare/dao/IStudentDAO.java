package com.fpoly.iocare.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.iocare.model.Student;

public interface IStudentDAO extends JpaRepository<Student, String>{

}
