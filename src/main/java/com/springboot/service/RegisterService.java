package com.springboot.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.repository.custom.RegistrationRepository;

@Service("registerService")
public class RegisterService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	RegistrationRepository registrationRepository;
	
	
	public void insertUser(String name, String email, String position,
			String password) {
		
	registrationRepository.insertUser(em, name, email, position, password);
		
		
	}


	

}
