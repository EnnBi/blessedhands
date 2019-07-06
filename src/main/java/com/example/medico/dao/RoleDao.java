package com.example.medico.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.medico.model.Role;

public interface RoleDao extends CrudRepository<Role, Long> {

	public Role findByName(String name);
}
