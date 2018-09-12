package com.example.medico.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.medico.custom.dao.MedicineCustomDao;
import com.example.medico.model.Medicine;

public interface MedicineDao extends CrudRepository<Medicine, Long>,MedicineCustomDao {

}
