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


@Repository
@Transactional
public class PositionRepository {
	
	public List<Position> retrievePositions(EntityManager em) {
		List<Position> data = null;
		
		Session session = em.unwrap(Session.class);		
		StringBuilder stringQuery = new StringBuilder("SELECT * FROM Position");
		SQLQuery query = session.createSQLQuery(stringQuery.toString());
		
		query.setResultTransformer(Transformers.aliasToBean(Position.class));
		data = query.list();		
		
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

	public void editPosition(EntityManager em, String id, String description) {
		StringBuilder stringQuery = new StringBuilder("UPDATE Position SET description = :description WHERE id = :id");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("description", description);
		query.setParameter("id", Integer.parseInt(id));
		query.executeUpdate();
	}
	
	public int removePosition(EntityManager em, String id) throws DataIntegrityViolationException {
		StringBuilder stringQuery = new StringBuilder("DELETE Position WHERE id = :id");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("id", Integer.parseInt(id));
		return query.executeUpdate();
	}
}
