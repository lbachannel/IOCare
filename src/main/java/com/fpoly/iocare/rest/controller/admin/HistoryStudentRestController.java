package com.fpoly.iocare.rest.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.iocare.model.HistoryStudent;
import com.fpoly.iocare.service.IHistoryStudentService;
import com.fpoly.iocare.service.impl.HistoryStudentServiceImpl;

@CrossOrigin("*")
@RestController
public class HistoryStudentRestController {
	@Autowired
	IHistoryStudentService historyStudentService = new HistoryStudentServiceImpl();

	/*--Lấy tất cả lịch sử sinh viên--*/
	@GetMapping("/rest/historystudent")
	public ResponseEntity<List<HistoryStudent>> findAll() {
		List<HistoryStudent> history = historyStudentService.findAll();
		if (history.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(history);
	}

	/*--Tạo mới 1 lịch sử mới--*/
	@PostMapping("/rest/historystudent")
	public ResponseEntity<HistoryStudent> post(@RequestBody HistoryStudent historyStudent) {
		historyStudent.setTakeCareTime(new Date());
		System.out.println(historyStudent.getTakeCareTime());
		historyStudentService.create(historyStudent);
		return ResponseEntity.ok(historyStudent);
	}
}
