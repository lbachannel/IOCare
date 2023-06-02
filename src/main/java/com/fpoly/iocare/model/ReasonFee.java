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
@Table(name = "ReasonFees")
public class ReasonFee {
    @Id
    @Column(name = "Reasonfeeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reasonFeeId;
    
    @Column(name = "Reasonfeename")
    private String reasonFeeName;
}
