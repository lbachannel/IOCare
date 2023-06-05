package com.fpoly.iocare.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JoinCampains")
public class JoinCampains {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JoinCampainsid")
	private Integer joinCampainsId;
	
	@ManyToOne
	@Column(name = "Campaignid")
	private Campain campain;
	
	@ManyToOne
	@Column(name = "Studentid")
	private Student student;
	
	@ManyToOne
	@Column(name = "Studyingstatusid")
	private StudyingStatus studyingStatus;
	
	@ManyToOne
	@Column(name = "Semesterid")
	private Semester semester;
	
	@ManyToOne
	@Column(name = "ReasonAbsenteeismid")
	private ReasonAbsenteeisms reasonAbsenteeisms;
	
	@ManyToOne
	@Column(name = "ReasonFeeId")
	private ReasonFee reasonFee;
}
