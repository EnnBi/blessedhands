package com.example.medico.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.medico.custom.dao.UserCustomDao;
import com.example.medico.model.VerificationToken;

@Repository
@Transactional
public class UserDaoImpl implements UserCustomDao{

	
	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public void createVerificationToken(VerificationToken verificationToken) {
		em.persist(verificationToken);
	}


	@Override
	public VerificationToken getVerificationToken(String token) {
		Query query = em.createQuery("Select v from VerificationToken v where v.token = :token").setParameter("token",token);
		return (VerificationToken) query.getSingleResult();
	}

	@Override
	public void deleteVerificationToken(VerificationToken verificationToken) {
		em.remove(verificationToken);
	}

}
