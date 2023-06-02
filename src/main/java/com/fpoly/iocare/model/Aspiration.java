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
@Table(name = "Aspirations")
public class Aspiration {
    @Id
    @Column(name = "Aspirationid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aspirationId;
    
    @Column(name = "Aspirationdes")
    private String aspirationDes;
}
