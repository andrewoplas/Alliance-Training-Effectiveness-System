package com.springboot.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.springboot.entities.Position;
import com.springboot.entities.User;


@Repository
@Transactional
public class PositionRepository {
	
	public List<Position> retrievePositions(EntityManager em) {
		List<Position> data = null;
		StringBuilder sql = new StringBuilder("FROM Position");
		Query query = em.createQuery(sql.toString());
		data = query.getResultList();		
		
		return data;
	}
	
	public int insertPosition(EntityManager em, Position position) {
		em.persist(position);
		em.flush();
		
		return position.getId();
	}
	
	public boolean contains(EntityManager em, String description) {
		StringBuilder stringQuery = new StringBuilder("FROM Position WHERE description = :description");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("description", description);
		
		return !query.getResultList().isEmpty();
	}

	public void editPosition(EntityManager em, int id, String description) {
		String sql = "UPDATE Position SET description = :description WHERE id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("description", description);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	public void removePosition(EntityManager em, int id) throws DataIntegrityViolationException {
		String sql = "DELETE FROM Position WHERE id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
