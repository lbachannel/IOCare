package com.fpoly.iocare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.iocare.dao.ISemesterDAO;
import com.fpoly.iocare.model.Semester;
import com.fpoly.iocare.service.ISemesterService;

@Service
public class SemesterServiceImpl implements ISemesterService{
	
	@Autowired
	ISemesterDAO dao;

	/*--Thêm mới học kỳ--*/
	@Override
	public Semester create(Semester semester) {
		return dao.save(semester);
	}

}
