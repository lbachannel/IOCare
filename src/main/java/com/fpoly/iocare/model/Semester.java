package com.fpoly.iocare.model;

import java.util.Date;

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
@Table(name = "Semesters")
public class Semester {
	@Id
	@Column(name = "Semesterid")
	private String semesterId;
	
	@Column(name = "Semestername")
	private String semesterName;
	
	@Column(name = "Starttime")
	private Date startTime = new Date();
	
	@Column(name = "Endtime")
	private Date endTime = new Date();
}
