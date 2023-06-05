package com.fpoly.iocare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Riskclassification")
public class RiskClassification {
	@Id
	@Column(name = "Riskclassificationid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer riskClassificationId;
	
	@Column(name = "Riskclassificationdes")
	private String riskClassificationDes;
}
