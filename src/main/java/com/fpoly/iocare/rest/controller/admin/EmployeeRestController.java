package com.fpoly.iocare.rest.controller.admin;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.iocare.model.Authority;
import com.fpoly.iocare.model.Employee;
import com.fpoly.iocare.model.Role;
import com.fpoly.iocare.model.Semester;
import com.fpoly.iocare.service.IAuthorityService;
import com.fpoly.iocare.service.IEmployeeService;
import com.fpoly.iocare.service.ISemesterService;
import com.fpoly.iocare.service.impl.AuthorityServiceImpl;
import com.fpoly.iocare.service.impl.EmployeeServiceImpl;
import com.fpoly.iocare.service.impl.SemesterServiceImpl;

@CrossOrigin("*")
@RestController
public class EmployeeRestController {
	@Autowired
	IEmployeeService employeeService = new EmployeeServiceImpl();
	
	@Autowired
	BCryptPasswordEncoder pe;
	@Autowired
	IAuthorityService authorityService = new AuthorityServiceImpl();
	
	@PostMapping("/rest/employee")
	public ResponseEntity<Employee> post(@RequestBody Employee employee){
		System.out.println("check");
		String password = pe.encode(employee.getEmployeePassword());
		employee.setEmployeePassword(password);
		String employeeId = employee.getEmployeeId();
		employeeService.create(employee);
		Authority authority = new Authority();
		authority.setEmployee(employee);
		System.out.println("check2");

		Role role = new Role();
		// set role mặc định tùy vào cách đặt trong DB
		role.setRoleId("1");
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		authority.setRole(role);
		
		authorityService.create(authority);
		return ResponseEntity.ok(employee);
	}
	
}
