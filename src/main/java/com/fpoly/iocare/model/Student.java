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
	private Semester semester;
	
	@Column(name = "Subjectid")
	private String subjectId;
	
	@Column(name = "Totalfee")
	private Double totalFee;
	
	@Column(name = "Major")
	private String major;
	
	@ManyToOne
	@JoinColumn(name = "Employeeid")
	private Employee employee;
	
	@Column(name = "Takecaretime")
	private Date takeCareTime = new Date();
	
	@ManyToOne
	@JoinColumn(name = "Aspirationid")
	private Aspiration aspiration;
	
	@ManyToOne
	@JoinColumn(name = "Objclassificationid")
	private ObjClassification objClassification;
	
	@ManyToOne
	@JoinColumn(name = "Riskclassificationid")
	private RiskClassification riskClassification;
	
	@Column(name = "DescriptionDetailsid")
	private String DescriptionDetailsId;
	
	@ManyToOne
	@JoinColumn(name = "Importedfilename")
	private ImportedData importedData;
	
	@OneToMany(mappedBy = "student")
	private List<JoinCampaign> joinCampains = new ArrayList<>();
}
