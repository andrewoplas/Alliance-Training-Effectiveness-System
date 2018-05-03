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

	public List<User> checkCredentials(EntityManager em, String email, String password) {
		// TODO Auto-generated method stub
		List<User> ListOfUser =null;
		StringBuilder employeeQuery = new StringBuilder("FROM USER WHERE email= :email AND password= :password");
		Query query = em.createQuery(employeeQuery.toString());
		query.setParameter("email", email);
		query.setParameter("password", password);
		ListOfUser = query.getResultList();
		return ListOfUser;
		
		
	}

}
