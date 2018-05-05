package com.springboot.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springboot.entities.Trainingplan;
import com.springboot.service.User;

@Repository
@Transactional
public class UserRepository {

	public void addUser(EntityManager em,User user) {
		em.persist(user);
	}

	public List<User> getUserByName(EntityManager em, String name) {
		List<User> userList = null;
		StringBuilder userQuery = new StringBuilder("FROM User WHERE name LIKE :name");
		Query query = em.createQuery(userQuery.toString());
		query.setParameter("name", "%" + name + "%");
		
		userList = query.getResultList();
		return userList;
	}

	public List<User> getUser(EntityManager em) {
		StringBuilder userQuery = new StringBuilder("FROM User");
		Query query = em.createQuery(userQuery.toString());
		List<User> userList = query.getResultList();
		return userList;
	}

	public User getSingleUserById(EntityManager em, String uid) {
		User user = null;
		int userId = Integer.parseInt(uid);
		StringBuilder userQuery = new StringBuilder("FROM User WHERE id = :id");
		Query query = em.createQuery(userQuery.toString());
		query.setParameter("id", userId);
		
		user = (User) query.getSingleResult();
		return user;
	}

	public User updateUser(EntityManager em, User outdatedUser) {
		int id = outdatedUser.getId();
		em.merge(outdatedUser);
		
		StringBuilder userQuery = new StringBuilder("FROM User WHERE id = :id");
		Query query = em.createQuery(userQuery.toString());
		query.setParameter("id", id);
		
		User user = (User) query.getSingleResult();
		return user;
	}

	public void removeUserById(EntityManager em, String uid) {
		int uID = Integer.parseInt(uid);
		StringBuilder userQuery = new StringBuilder("DELETE FROM User WHERE id = :id");
		Query query = em.createQuery(userQuery.toString());
		query.setParameter("id",uID);
		query.executeUpdate();
	}

}
