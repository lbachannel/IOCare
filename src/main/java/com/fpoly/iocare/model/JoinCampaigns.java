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
@Table(name = "Joincampaigns")
public class JoinCampaigns {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Joincampaignsid")
	private Integer joinCampaignsId;
	
	@ManyToOne
	@JoinColumn(name = "Campaignid")
	private Campaign campaign;
	
	@ManyToOne
	@JoinColumn(name = "Studentid")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "Studyingstatusid")
	private StudyingStatus studyingStatus;
	
	@ManyToOne
	@JoinColumn(name = "Semesterid")
	private Semester semester;
	
	@ManyToOne
	@JoinColumn(name = "Reasonabsenteeismid")
	private ReasonAbsenteeism reasonAbsenteeisms;
	
	@ManyToOne
	@JoinColumn(name = "Reasonfeeid")
	private ReasonFee reasonFee;
}
