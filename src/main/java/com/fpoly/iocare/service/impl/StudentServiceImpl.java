package com.fpoly.iocare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.iocare.dao.IStudentDAO;
import com.fpoly.iocare.model.Student;
import com.fpoly.iocare.service.IStudentService;

@Service
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

	/*--Hiển thị tất cả sinh viên--*/
	@Override
	public List<Object[]> findAll(String importFileName) {
		return dao.findAll(importFileName);
	}

}