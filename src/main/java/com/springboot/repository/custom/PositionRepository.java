package com.springboot.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
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
}
