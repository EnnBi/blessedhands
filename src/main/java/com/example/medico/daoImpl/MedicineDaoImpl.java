package com.example.medico.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.medico.custom.dao.MedicineCustomDao;
import com.example.medico.model.Medicine;

@Repository
public class MedicineDaoImpl implements MedicineCustomDao{

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Medicine> getMedicinePaginated(int from, int rows) {
		Query query = em.createQuery("Select m from Medicine m");	
		return query.setFirstResult(from).setMaxResults(rows).getResultList();
	}

	@Override
	public List<Medicine> getMedicinesOnSearch(String search,int from,int rows){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Medicine> cq = cb.createQuery(Medicine.class);
		Root<Medicine> root =cq.from(Medicine.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		System.err.println(search.toLowerCase()+"-----------search----------"+from);
		predicates.add(cb.like(root.<String>get("name"),"%"+search+"%"));
		/*predicates.add(cb.like(cb.lower(root.get("company").get("name")),"%"+search.toLowerCase()+"%"));
		predicates.add(cb.like(cb.lower(root.get("type").get("name")),"%"+search.toLowerCase()+"%"));
		*/
		cq.select(root).where(cb.or(predicates.toArray(new Predicate[] {})));
		cq.distinct(true);
		
		return em.createQuery(cq).setFirstResult(from).setMaxResults(rows).getResultList();
	}
	
	
	
	@Override
	public long getMedicineCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
