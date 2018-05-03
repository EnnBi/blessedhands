package com.example.medico.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.medico.model.User;

public interface UserDao extends CrudRepository<User, Long>{
	public User findByEmail(String email);
}
