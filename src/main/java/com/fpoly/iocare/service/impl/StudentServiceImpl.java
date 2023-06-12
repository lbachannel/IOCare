package com.fpoly.iocare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fpoly.iocare.dao.IStudentDAO;
import com.fpoly.iocare.model.Student;
import com.fpoly.iocare.service.IStudentService;

public class StudentServiceImpl implements IStudentService{
	
	@Autowired
	IStudentDAO dao;

	@Override
	public Student create(Student student) {
		return dao.save(student);
	}

	@Override
	public boolean existsById(String id) {
		if(dao.existsById(id))
			return true;
		return false;
	}

}
