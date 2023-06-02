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
@Table(name = "ObjClassification")
public class ObjClassification {
	@Id
	@Column(name = "Objclassificationid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer objClassificationId;
	
	@Column(name = "Objclassificationdes")
	private String objClassification;
	
}
