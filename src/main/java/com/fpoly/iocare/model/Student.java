package com.fpoly.iocare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Students")
public class Student {
	@Id
	@Column(name = "Studentid")
	private String studentId;
	
	@ManyToOne
	@JoinColumn(name = "Semesterid")
	private Semester semesterId;
	
	@Column(name = "SubjectId")
	private String subjectId;
	
	@Column(name = "TotalFee")
	private Double totalFee;
	
	@Column(name = "Major")
	private String major;
	
	@ManyToOne
	@JoinColumn(name = "EmployeeId")
	private Employees employees;
	
	@Column(name = "Takecaretime")
	private Date takeCareTime = new Date();
	
	@ManyToOne
	@JoinColumn(name = "Aspirationid")
	private Aspirations aspirations;
	
	@ManyToOne
	@JoinColumn(name = "Objclassificationid")
	private ObjClassification objClassifications;
	
	@ManyToOne
	@JoinColumn(name = "Riskclassificationid")
	private RiskClassification riskClassifications;
	
	@Column(name = "DescriptionDetailsid")
	private String DescriptionDetailsId;
	
	@ManyToOne
	@JoinColumn(name = "Importedid")
	private ImportedData importedData;
	
	@OneToMany(mappedBy = "Stundent")
	List<JoinCampains> joinCampains = new ArrayList<>();
}
