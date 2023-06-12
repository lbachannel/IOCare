package com.fpoly.iocare.model;

import java.util.ArrayList;
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
public class Campaign {
	@Id
	@Column(name = "Campaignid")
	private String campaignId;
	
	@Column(name = "Campaignname")
	private String campaignName;
	
	@OneToMany(mappedBy = "")
	private List<JoinCampaign> joinCampaigns = new ArrayList<>();
	
	@OneToMany(mappedBy = "campaign")
	private List<ImportedData> importedDatas = new ArrayList<>();
}
