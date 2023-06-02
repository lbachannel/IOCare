package com.fpoly.iocare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
}
