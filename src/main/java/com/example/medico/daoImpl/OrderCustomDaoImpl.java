package com.example.medico.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.medico.custom.dao.OrderCustomDao;

@Repository
@Transactional
public class OrderCustomDaoImpl implements OrderCustomDao {

	@PersistenceContext
	EntityManager em;
	 
	/*@Override
	public Long getMaxId() {
		Query query = em.createQuery("Select Max(o.id) from Orders o");
		return (Long) query.getSingleResult();
	}*/

}
