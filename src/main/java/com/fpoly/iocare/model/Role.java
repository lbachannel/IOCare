package com.fpoly.iocare.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Roles")
public class Role {
	@Id
	@Column(name = "Roleid")
	private String roleId;
	
	@Column(name = "Rolename")
	private String roleName;
	
	@OneToMany(mappedBy = "role")
	private List<Authority> authority = new ArrayList<>();
}
