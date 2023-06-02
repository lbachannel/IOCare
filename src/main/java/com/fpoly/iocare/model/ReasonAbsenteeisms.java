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
@Table(name = "ReasonAbsenteeisms")
public class ReasonAbsenteeisms {
    @Id
    @Column(name = "Reasonabsenteeismid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reasonAbsenteeismId;
    
    @Column(name = "Reasonabsenteeismname")
    private String reasonAbsenteeismName;
}
