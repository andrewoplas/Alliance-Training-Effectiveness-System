package com.springboot.service;

import java.nio.charset.StandardCharsets;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.springboot.entities.User;
import com.springboot.repository.custom.LoginRepository;

@Service
public class LoginService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private LoginRepository loginRepository;

	public User searchUser(String email, String password) {
		password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		return loginRepository.searchUser(em, email, password);
	}

}
