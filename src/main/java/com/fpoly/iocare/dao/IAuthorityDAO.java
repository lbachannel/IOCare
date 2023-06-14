package com.fpoly.iocare.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.iocare.model.Authority;
import com.fpoly.iocare.model.Employee;

@Repository
public interface IAuthorityDAO extends JpaRepository<Authority, Integer>{
	@Query("select distinct a from Authority a where a.employee in ?1")
	List<Authority> authoritiesOf(List<Employee> employees);
}