package com.fpoly.iocare.service;

import java.util.List;

import com.fpoly.iocare.model.Student;

public interface IStudentService {
	/*--Thêm mới student--*/
	Student create(Student student);

	/*--Kiểm tra mã student có tồn tại hay chưa--*/
	boolean existsById(String studentId);
	
	/*--Hiển thị tất cả sinh viên--*/
	List<Object[]> findAll(String importFileName);
}
