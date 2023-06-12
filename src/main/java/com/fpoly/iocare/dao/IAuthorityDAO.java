package com.fpoly.iocare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.iocare.model.Authority;

@Repository
public interface IAuthorityDAO extends JpaRepository<Authority, Integer>{

}
