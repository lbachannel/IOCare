//package com.fpoly.iocare.model;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "ImportedData")
//public class ImportedData {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer importedId;
//	
//	@ManyToOne
//	@Column(name = "Semesterid")
//	private Semester semester;
//	
//	@Column(name = "Campaignid")
//	private String CampaignId;
//	
//	@Column(name = "Importedfilename")
//	private String importFileName;
//	
//	@Column(name = "ImportedDate")
//	private Date importedDate = new Date();
//	
//	@ManyToOne
//	@Column(name = "Employeeid")
//	private Employees employees;
//	
//	@OneToMany(mappedBy = "importeddata")
//	private List<Student> students = new ArrayList<>();
//}
