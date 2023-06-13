package com.fpoly.iocare.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "ReasonAbsenteeisms")
public class ReasonAbsenteeism {
    @Id
    @Column(name = "Reasonabsenteeismid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reasonAbsenteeismId;
    
    @Column(name = "Reasonabsenteeismname")
    private String reasonAbsenteeismName;
    
    @OneToMany(mappedBy = "reasonAbsenteeisms")
    private List<JoinCampaigns> joinCampains = new ArrayList<>();
}
