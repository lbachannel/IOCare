package com.fpoly.iocare.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private LocalDate startTime = LocalDate.now() ;
	
	@Column(name = "Endtime")
	private LocalDate endTime = LocalDate.now();
	
	@OneToMany(mappedBy = "semester")
	private List<JoinCampaign> joinCampaigns = new ArrayList<>();
	
	@OneToMany(mappedBy = "semester")
	private List<ImportedData> importedDatas = new ArrayList<>();
	
	@OneToMany(mappedBy = "semester")
	private List<Student> students = new ArrayList<>();
}
