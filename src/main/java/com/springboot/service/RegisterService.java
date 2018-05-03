package com.springboot.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.User;
import com.springboot.repository.custom.RegisterRepository;

@Service("registerService")
public class RegisterService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	RegisterRepository registerRepository;
	
	public boolean containsUserByEmail(String email) {
		return registerRepository.contains(em, email);
	}
	
	public String insertUser(String name, String email, String position, String password, String confirm) {
		String response = null;
		if(!registerRepository.contains(em, email)) {
			response = "exist";
		} else if (!password.equals(confirm)) {
			response = "password_does_not_match";
		} else {
			registerRepository.insertUser(em, name, email, position, password);
		}
		
		return response;
	}
}
