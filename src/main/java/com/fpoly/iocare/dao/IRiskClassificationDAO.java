package com.fpoly.iocare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.iocare.model.RiskClassification;

@Repository
public interface IRiskClassificationDAO extends JpaRepository<RiskClassification, Integer>{
	
}