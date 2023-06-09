package com.fpoly.iocare.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employees")
public class Employees {
	@Id
	@Column(name = "EmployeeId")
	private String employeeId;
	
	@Column(name = "EmployeePassword")
	private String employeePassword;
	
	@Column(name = "EmployeeName")
	private String employeeName;
	
	@Column(name = "EmpoyeeEmail")
	private String empoyeeEmail;
	
	@Column(name = "EmployeePhone")
	private String employeePhone;
}
