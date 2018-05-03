package com.example.medico.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.medico.dao.GeneralDao;
import com.example.medico.model.Company;
import com.example.medico.model.Medicine;
import com.example.medico.model.Role;
import com.example.medico.model.Type;
import com.example.medico.model.Unit;
import com.example.medico.model.User;

@Repository
@Transactional
public class GeneralDaoImpl implements GeneralDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean isTypePresent(Type type, boolean save) {
		if(save) {
			Query query = em.createQuery("Select t from Type t where t.name = :name").setParameter("name",type.getName());
			if(query.getResultList().isEmpty())
				return false;
			return true;
		}
		else{
			Query query = em.createQuery("Select t from Type t where t.name = :name and t.id != :id ")
										.setParameter("name",type.getName()).setParameter("id",type.getId());
			if(query.getResultList().isEmpty())
				return false;
			return true;
		}
	}

	@Override
	public boolean isUnitPresent(Unit unit, boolean save) {
		if(save) {
			Query query = em.createQuery("Select u from Unit u where u.name = :name").setParameter("name",unit.getName());
			if(query.getResultList().isEmpty())
				return false;
			return true;
		}
		else{
			Query query = em.createQuery("Select u from Unit u where u.name = :name and u.id != :id ").
										setParameter("name",unit.getName()).setParameter("id",unit.getId());
			if(query.getResultList().isEmpty())
				return false;
			return true;
		}
	
	}

	@Override
	public boolean isCompanyPresent(Company company, boolean save) {
		if(save) {
			Query query = em.createQuery("Select c from Company c where c.name = :name").setParameter("name",company.getName());
			if(query.getResultList().isEmpty())
				return false;
			return true;
		}
		else{
			Query query = em.createQuery("Select c from Company c where c.name = :name and c.id != :id ")
										.setParameter("name",company.getName()).setParameter("id",company.getId());
			if(query.getResultList().isEmpty())
				return false;
			return true;
		}
	}

	@Override
	public boolean isMedicinePresent(Medicine medicine, boolean save) {
		if(save) {
			Query query = em.createQuery("Select m from Medicine m where m.name = :name").setParameter("name",medicine.getName());
			if(query.getResultList().isEmpty())
				return false;
			return true;
		}
		else{
			Query query = em.createQuery("Select m from Medicine m where m.name = :name and m.id != :id ")
										.setParameter("name",medicine.getName()).setParameter("id",medicine.getId());
			if(query.getResultList().isEmpty())
				return false;
			return true;
		}
		
	}

	@Override
	public boolean isOperatorPresent(User operator, boolean save) {
		if(save) {
			Query query = em.createQuery("Select u from User u where u.email = :email").setParameter("email",operator.getEmail());
			if(query.getResultList().isEmpty())
				return false;
			return true;
		}
		else{
			Query query = em.createQuery("Select u from User u where u.email = :email and u.id != :id ")
										.setParameter("email",operator.getEmail()).setParameter("id",operator.getId());
			if(query.getResultList().isEmpty())
				return false;
			return true;
		}
	}
	
	@Override
	public Role findRoleOnName(String name) {
		Query query = em.createQuery("Select r from Role r where r.name = :name").setParameter("name", name);
		return (Role) query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicine> findAllMedicinesOfUser(User user){
		Query query = em.createQuery("Select m from Medicine m where m.operator.id = :id").setParameter("id", user.getId());
		return query.getResultList();
	}
}
