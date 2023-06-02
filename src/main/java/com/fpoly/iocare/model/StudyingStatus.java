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
@Table(name = "StudyingStatus" )
public class StudyingStatus {
	@Id
	@Column(name = "Studyingstatusid")
	private String studyingStatusId;
	
	@Column (name = "Studyingstatusname")
	private String studyingStatusName;

}
