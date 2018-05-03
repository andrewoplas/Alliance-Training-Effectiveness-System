package com.springboot.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;




@Repository
@Transactional
public class RegistrationRepository {

	public void insertUser(EntityManager em, String name, String email,
			String position, String password) {
		// TODO Auto-generated method stub
		
		StringBuilder employeeQuery = new StringBuilder("INSERT INTO USER VALUES(:name,:position,:email,:password,:status,:isAdmin)");
		Query query = em.createQuery(employeeQuery.toString());
		query.setParameter("nameOfEmployee",name);
		query.setParameter("position",position);
		query.setParameter("email",email);
		query.setParameter("password",password);
		query.setParameter("status","pending");
		query.setParameter("isAdmin",0);
		query.executeUpdate();
		
	}

}
