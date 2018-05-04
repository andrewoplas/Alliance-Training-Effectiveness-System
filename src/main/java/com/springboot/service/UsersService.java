package com.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.User;
import com.springboot.repository.custom.LoginRepository;
import com.springboot.repository.custom.UsersRepository;

@Service
public class UsersService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsersRepository usersRepository;

	public List<User> retrievePendingUser() {
		List<User> users = usersRepository.retrievePendingUser(em);
		
		return users;
	}
	
	public void approveUser(String id) {
		try {
			int uid = Integer.parseInt(id);
			usersRepository.approveUser(em, uid);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void declineUser(String id) {
		try {
			int uid = Integer.parseInt(id);
			usersRepository.declineUser(em, uid);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
