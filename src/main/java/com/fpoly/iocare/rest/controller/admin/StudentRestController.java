package com.fpoly.iocare.rest.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	/*--Hiển thị tất cả sinh viên--*/
	@GetMapping("/rest/student/{importedFileName}")
	public ResponseEntity<?> findAll(@PathVariable("importedFileName") String importedFileName){
		/*--câu truy vấn trả về một danh sách các Object[]--*/
		List<Object[]> students = studentService.findAll(importedFileName);
		/*--// Nên cần chuyển đổi kết quả trả về thành một danh sách các Map<String, Object>--*/
		List<Map<String, Object>> response = new ArrayList<>();
		/*--xử lý chuyển đổi--*/
		for (Object[] row : students) {
			Map<String, Object> item = new HashMap<>();
			item.put("studentId", row[0]);
			item.put("semester", row[1]);
			item.put("subjectId", row[2]);
			item.put("totalFee", row[3]);
			item.put("major", row[4]);
			item.put("employee", row[5]);
			item.put("takeCareTime", row[6]);
			item.put("aspirations", row[7]);
			item.put("objClassifications", row[8]);
			item.put("riskClassifications", row[9]);
			item.put("DescriptionDetailsId", row[10]);
			item.put("imported", row[11]);
			response.add(item);
		}
		return ResponseEntity.ok(response);
	}
	
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
