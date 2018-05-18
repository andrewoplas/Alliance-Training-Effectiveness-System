package com.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.UserEvent;
import com.springboot.repository.custom.UserTrainingRepository;

@Service
public class UserTrainingService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserTrainingRepository tpRepository;
	
	public List<UserEvent> retrieveUserEvent(int userID) {
		return tpRepository.retrieveUserEvent(em, userID) ;
	}
	
	public List<UserEvent> retrievePendingUserEvent(int userID) {
		return tpRepository.retrievePendingUserEvent(em, userID) ;
	}
	
	public void acceptInvitation(String id) {
		tpRepository.acceptInvitation(em, Integer.parseInt(id));
	}
	
	public void declineInvitation(String id) {
		tpRepository.declineInvitation(em, Integer.parseInt(id));
	}
}