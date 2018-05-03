package com.example.medico.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.medico.model.Company;

public interface CompanyDao extends CrudRepository<Company, Long> {

}
