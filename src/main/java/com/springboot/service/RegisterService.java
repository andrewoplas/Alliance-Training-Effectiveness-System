package com.springboot.service;

import java.nio.charset.StandardCharsets;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.springboot.entities.Position;
import com.springboot.entities.User;
import com.springboot.repository.custom.RegisterRepository;

@Service("registerService")
public class RegisterService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private RegisterRepository registerRepository;
	
	public boolean containsUserByEmail(String email) {
		return registerRepository.contains(em, email);
	}
	
	public String insertUser(String name, String email, String position, String password, String confirm) {
		String response = null;
		if(registerRepository.contains(em, email)) {
			response = "exist";
		} else if (!password.equals(confirm)) {
			response = "password_does_not_match";
		} else {
			// Generate hashed password
			String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
			
			User user = new User();
			Position pos = new Position();
			pos.setId(Integer.parseInt(position));
			user.setName(name);
			user.setPosition(pos);
			user.setEmail(email);
			user.setPassword(hashedPassword);
			user.setStatus("pending");
			
			registerRepository.insertUser(em, user);
		}
		
		return response;
	}
}
