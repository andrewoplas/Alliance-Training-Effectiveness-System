package com.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.User;
import com.springboot.repository.custom.LoginRepository;

@Service
public class LoginService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private LoginRepository loginRepository;

	public User searchUser(String email, String password) {
		User user = loginRepository.searchUser(em, email, password);
		
		return user;
	}

}
