package com.example.medico.custom.dao;

import java.util.List;

import com.example.medico.model.Medicine;

public interface MedicineCustomDao {

	public List<Medicine> getMedicinePaginated(int from,int rows);
	public long getMedicineCount();
	public List<Medicine> getMedicinesOnSearch(String search, int from, int rows);
}
