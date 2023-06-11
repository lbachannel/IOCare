package com.fpoly.iocare.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employees")
public class Employee {
	
	@Id
	@Column(name = "employeeid")
	private String id;
	
	@Column(name = "employeename")
	private String name;
	
	@Column(name = "employeepassword")
	private String pasword;
	
	@Column(name = "empoyeeemail")
	private String email;
	
	@Column(name = "employeephone")
	private String phone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
	List<Authority> authorities;
}
