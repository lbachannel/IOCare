package com.fpoly.iocare.model;

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
@Table(name = "Campains")
public class Campain {
	@Id
	@Column(name = "Cammpaignid")
	private String campaignId;
	
	@Column(name = "CampaignName")
	private String campaignName;
	
	@OneToMany(mappedBy = "campain")
	private List<JoinCampains> joinCampains = new ArrayList<>();
}
