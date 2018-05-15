package com.springboot.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.springboot.entities.User;

@Repository
@Transactional
public class RegisterRepository {
	
	public boolean contains(EntityManager em, String email) {
		// Check if user exist through checking email
		StringBuilder stringQuery = new StringBuilder("FROM User WHERE email = :email");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("email", email);
		
		return !query.getResultList().isEmpty();
	}

	public void insertUser(EntityManager em, User user) {
		// Insert if no duplicate email
		em.persist(user);
	}

}
