package com.springboot.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.springboot.entities.User;


@Repository
@Transactional
public class UsersRepository {
	
	public boolean containsByIdAndString(EntityManager em, String email, String id) {
		StringBuilder stringQuery = new StringBuilder("FROM User WHERE email = :email AND id != :id");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("email", email);
		query.setParameter("id", Integer.parseInt(id));
		
		return !query.getResultList().isEmpty();
	}
	
	public List retrieveUsers(EntityManager em) {
		List data = null;
		
		Session session = em.unwrap(Session.class);		
		StringBuilder stringQuery = new StringBuilder("SELECT u.id, u.name, p.description, u.email, t.title, e.role, u.is_admin FROM user_event e INNER JOIN training_plan t ON t.id = e.trainingPlanID RIGHT JOIN User u ON e.userID = u.id LEFT JOIN Position p ON p.id = u.position  WHERE u.status = :status");
		SQLQuery query = session.createSQLQuery(stringQuery.toString());
		query.setParameter("status", "approved");
		data = query.list();		
		
		return data;		
	}
	
	public List<User> retrieveApprovedUsers(EntityManager em) {
		List<User> users = null;
		StringBuilder stringQuery = new StringBuilder("FROM User WHERE status = :status AND is_admin = :is_admin");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("status", "approved");
		query.setParameter("is_admin", 0);
		
		users = query.getResultList();
		return users;		
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
	
	public int removeUser(EntityManager em, int id) {
		String sql = "DELETE FROM User WHERE id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public int approveUser(EntityManager em, int id) {		
		String sql = "UPDATE User SET status = :status WHERE id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("status", "approved");
		query.setParameter("id", id);
		return query.executeUpdate();
	}
	
	public int declineUser(EntityManager em, int id) {
		String sql = "DELETE FROM User WHERE id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	public User retrieveUser(EntityManager em, String id) {
		User user = null;
		StringBuilder stringQuery = new StringBuilder("FROM User WHERE id = :id");
		Query query = em.createQuery(stringQuery.toString());
		query.setParameter("id", Integer.parseInt(id));
		
		try {
			user = (User)query.getSingleResult();
		} catch (NonUniqueResultException ex) {
			ex.printStackTrace();
			user = null;
		} catch (NoResultException ex) {
			ex.printStackTrace();
			user = null;
		}
		
		return user;
	}

	public void editUser(EntityManager em, User user) {
		User updateUser = em.find(User.class, user.getId());
			
		updateUser.setName(user.getName());
		updateUser.setPosition(user.getPosition());
		updateUser.setEmail(user.getEmail());
		
		if(user.getPassword() != null && !user.getPassword().isEmpty()) {
			updateUser.setPassword(user.getPassword());
		}		
	}
	
	public void editUserByView(EntityManager em, User user) {
		User updateUser = em.find(User.class, user.getId());
			
		updateUser.setName(user.getName());
		updateUser.setPosition(user.getPosition());

		if(user.getPassword() != null && !user.getPassword().isEmpty()) {
			updateUser.setPassword(user.getPassword());
		}	
	}
	
	
}
