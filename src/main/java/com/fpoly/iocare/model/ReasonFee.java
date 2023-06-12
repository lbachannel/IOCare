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
@Table(name = "ReasonFees")
public class ReasonFee {
    @Id
    @Column(name = "Reasonfeeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reasonFeeId;
    
    @Column(name = "Reasonfeename")
    private String reasonFeeName;
    
    @OneToMany(mappedBy = "reasonFee")
    private List<JoinCampaign> joinCampaigns = new ArrayList<>();
}
