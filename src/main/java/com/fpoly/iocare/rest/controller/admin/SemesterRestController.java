package com.fpoly.iocare.rest.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.iocare.model.Semester;
import com.fpoly.iocare.service.ISemesterService;
import com.fpoly.iocare.service.impl.SemesterServiceImpl;

@CrossOrigin("*")
@RestController
public class SemesterRestController {
	@Autowired
	ISemesterService semesterService = new SemesterServiceImpl();
	
	/*--Tạo mới 1 học kỳ--*/
	@PostMapping("/rest/semester")
	public ResponseEntity<Semester> post(@RequestBody Semester semester){
		semesterService.create(semester);
		return ResponseEntity.ok(semester);
	}
}
