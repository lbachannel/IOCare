package com.fpoly.iocare.service;

import java.util.List;

import com.fpoly.iocare.model.Authority;

public interface IAuthorityService {

	Authority create(Authority authority);
	
	List<Authority> findAll();
	
	void delete(Integer id);
	
	List<Authority> findAuthoritiesOfAdministrators();
	
}