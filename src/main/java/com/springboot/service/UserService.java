package com.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.controller.User;
import com.springboot.entities.Trainingplan;
import com.springboot.repository.custom.UserRepository;

@Service
public class UserService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserRepository userRepository;
	
	public void addUser(String name, String position, String email,
			String password, String status, int isAdmin) {
		User user = new User();
		user.setName(name);
		user.setPosition(position);
		user.setEmail(email);
		user.setPassword(password);
		user.setStatus(status);
		user.setIsAdmin(isAdmin);
		userRepository.addUser(em,user);
	}

	public List<User> getUserByName(String name) {
		return userRepository.getUserByName(em,name);
	}

	public List<User> getUser() {
		return userRepository.getUser(em);
	}

	public User updateUser(User participant, String uid) {
		User outdatedParticipant = userRepository.getSingleUserById(em,uid);
		outdatedParticipant.setName(participant.getName());
		outdatedParticipant.setPosition(participant.getPosition());
		outdatedParticipant.setEmail(participant.getEmail());
		outdatedParticipant.setPassword(participant.getPassword());
		outdatedParticipant.setStatus(participant.getStatus());
		outdatedParticipant.setIsAdmin(participant.getIsAdmin());
		return userRepository.updateUser(em,outdatedParticipant);
	}

	public User getSingleUserById(String uid) {
		return userRepository.getSingleUserById(em,uid);
	}

	public void removeUserById(String uid) {
		userRepository.removeUserById(em,uid);
	}

}
