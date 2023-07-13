package com.fpoly.iocare.service;

import java.util.List;

import com.fpoly.iocare.model.HistoryStudent;

public interface IHistoryStudentService {
	/*--Thêm mới lịch sử sinh viên--*/
	HistoryStudent create(HistoryStudent historyStudent);
	
	/*--Lấy tất cả lịch sử sinh viên--*/
	List<HistoryStudent> findAll ();
	
	List<HistoryStudent> findById (String employeeId);
}
