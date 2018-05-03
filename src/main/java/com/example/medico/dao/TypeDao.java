package com.example.medico.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.medico.model.Type;

public interface TypeDao extends CrudRepository<Type, Long> {

}
