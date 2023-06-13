package com.fpoly.iocare.service;

import java.util.List;

import com.fpoly.iocare.model.Authority;
import com.fpoly.iocare.model.Employee;
import com.fpoly.iocare.model.Role;

public interface IRoleService {
		List<Role> findAll();
}
