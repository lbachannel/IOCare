package com.fpoly.iocare.service;

import com.fpoly.iocare.model.Student;

public interface IStudentService {
	/*--Thêm mới student--*/
	Student create(Student student);

	/*--Kiểm tra mã student có tồn tại hay chưa--*/
	boolean existsById(String studentId);
	
	
}
