package com.fpoly.iocare.service;

import com.fpoly.iocare.model.Student;

public interface IStudentService {
	/*--Thêm mới student--*/
	Student create(Student student);

	boolean existsById(String studentId);
	
	/*--Kiểm tra mã student có tồn tại hay chưa--*/
}
