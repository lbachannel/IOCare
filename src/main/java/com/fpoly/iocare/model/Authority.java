package com.fpoly.iocare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Authorities")
public class Authority {
	@Id
	@Column(name = "Authorityid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authorityId;
	
	@Column(name = "Employeeid")
	private String employeeId;
	
	
	
	/* Khóa ngoại Employee
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "Employeeid", referencedColumnName = "Employeeid") 
	 * private Employee employee;
	 */
	
	@ManyToOne
	@JoinColumn(name = "Roleid", referencedColumnName = "Roleid")
	private Role role;
	
}