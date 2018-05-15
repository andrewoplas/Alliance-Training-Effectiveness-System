package com.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.springboot.entities.Position;
import com.springboot.repository.custom.PositionRepository;

@Service
public class PositionService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PositionRepository positionRepository;
	
	public List<Position> retrievePositions() {
		return positionRepository.retrievePositions(em);
	}

	public String insertPosition(String description) {
		String result = "success";
		int id = 0;
		
		if(positionRepository.contains(em, description)) {
			result = "already_exists";
		} else {
			Position position = new Position();
			position.setDescription(description);
			
			id = positionRepository.insertPosition(em, position);
			result += ":" + id;
		}
		
		return result;
	}

	public String editPosition(String id, String description) {
		String result = "success";
		
		if(positionRepository.contains(em, description)) {
			result = "already_exists";
		} else {
			positionRepository.editPosition(em, id, description);
		}
		
		return result;
	}
	
	public String removePosition(String id) {
		String result = "success";
		
		try {
			positionRepository.removePosition(em, id);
		} catch (DataIntegrityViolationException ex) {
			result = "error";
		}
		
		return result;
	}
}
