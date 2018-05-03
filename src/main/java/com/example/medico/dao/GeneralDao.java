package com.example.medico.dao;

import java.util.List;

import com.example.medico.model.Company;
import com.example.medico.model.Medicine;
import com.example.medico.model.Role;
import com.example.medico.model.Type;
import com.example.medico.model.Unit;
import com.example.medico.model.User;

public interface GeneralDao {

	public boolean isTypePresent(Type type,boolean save);
	public boolean isUnitPresent(Unit unit,boolean save);
	public boolean isCompanyPresent(Company company,boolean save);
	public boolean isMedicinePresent(Medicine medicine,boolean save);
	public boolean isOperatorPresent(User operator,boolean save);
	public Role findRoleOnName(String name);
	public List<Medicine> findAllMedicinesOfUser(User user);
}
