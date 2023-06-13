package com.fpoly.iocare.rest.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.iocare.model.Student;
import com.fpoly.iocare.service.IStudentService;
import com.fpoly.iocare.service.impl.StudentServiceImpl;

@CrossOrigin("*")
@RestController
public class StudentRestController {
	@Autowired
	IStudentService studentService = new StudentServiceImpl();
	
	/*--Import students--*/
	@PostMapping("/rest/student")
	public ResponseEntity<Student> post(@RequestBody Student student){
		if(studentService.existsById(student.getStudentId())) {
			System.out.println(student.getStudentId());
			return ResponseEntity.badRequest().build();
		}
		studentService.create(student);
		return ResponseEntity.ok(student);
	}
}
