package com.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
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
}
