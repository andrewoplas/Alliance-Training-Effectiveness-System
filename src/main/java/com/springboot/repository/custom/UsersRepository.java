package com.springboot.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.springboot.entities.User;


@Repository
@Transactional
public class UsersRepository {
	
	public List retrieveUsers(EntityManager em) {
		List data = null;
		
		Session session = em.unwrap(Session.class);		
		StringBuilder stringQuery = new StringBuilder("SELECT u.id, u.name, u.position, u.email, t.title, p.role FROM Participant p INNER JOIN trainingplan t ON t.id = p.training_id RIGHT JOIN User u ON p.user_id = u.id WHERE u.status = :status");
		SQLQuery query = session.createSQLQuery(stringQuery.toString());
		query.setParameter("status", "approved");
		data = query.list();		
		
		return data;		
	}

	public List<User> retrievePendingUsers(EntityManager em) {
		List<User> users = null;
		StringBuilder stringQuery = new StringBuilder("FROM User WHERE status = :status AND is_admin = :is_admin");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("status", "pending");
		query.setParameter("is_admin", 0);
		
		users = query.getResultList();
		return users;		
	}
	
	public void removeUser(EntityManager em, int id) {
		StringBuilder stringQuery = new StringBuilder("DELETE User WHERE id = :id");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	public void approveUser(EntityManager em, int id) {
		StringBuilder stringQuery = new StringBuilder("UPDATE User SET status = :status WHERE id = :id");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("status", "approved");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	public void declineUser(EntityManager em, int id) {
		StringBuilder stringQuery = new StringBuilder("DELETE FROM User WHERE id = :id");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
