package com.springboot.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import com.springboot.entities.User;


@Repository
@Transactional
public class LoginRepository {

	public User searchUser(EntityManager em, String email, String password) {
		User user = null;
		StringBuilder stringQuery = new StringBuilder("FROM User WHERE email = :email AND password = :password");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		try {
			user = (User)query.getSingleResult();
		} catch(Exception ex) {
			user = null;
		}
		
		return user;		
	}

}
