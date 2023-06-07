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
@Table(name = "Campains")
public class Campain {
	@Id
	@Column(name = "Cammpaignid")
	private String campaignId;
	
	@Column(name = "CampaignName")
	private String campaignName;
}
