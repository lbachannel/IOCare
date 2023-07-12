package com.fpoly.iocare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.iocare.dao.IHistoryStudentDAO;
import com.fpoly.iocare.model.HistoryStudent;
import com.fpoly.iocare.service.IHistoryStudentService;

@Service
public class HistoryStudentServiceImpl implements IHistoryStudentService{
	
	@Autowired
	IHistoryStudentDAO dao;

	/*--Thêm mới lịch sử sinh viên--*/
	@Override
	public HistoryStudent create(HistoryStudent historyStudent) {
		return dao.save(historyStudent);
	}
	
	/*--Hiển thị tất cả lịch sử sinh viên--*/
	@Override
	public List<HistoryStudent> findAll() {
		return dao.findAll();
	}
	
}
