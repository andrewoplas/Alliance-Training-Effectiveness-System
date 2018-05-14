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

	public void insertUser(EntityManager em, String name, String email, String position, String password, String status) {
		// Insert if no duplicate email
		Session session = em.unwrap(Session.class);
		StringBuilder stringQuery = new StringBuilder("INSERT INTO User (name, position, email, password, status) VALUES (:name, :position, :email, :password, :status)");
		SQLQuery query = session.createSQLQuery(stringQuery.toString());
		query.setParameter("name", name);
		query.setParameter("position", position);
		query.setParameter("email", email);
		query.setParameter("password", password);
		query.setParameter("status", status);
		query.executeUpdate();
	}

}
