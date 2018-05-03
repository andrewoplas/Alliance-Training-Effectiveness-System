package com.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.User;
import com.springboot.repository.custom.LoginRepository;

@Service("loginService")
public class LoginService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	LoginRepository loginRepository;

	public boolean checkCredentials(String email, String password) {
		// TODO Auto-generated method stub
		
		List<User> listOfUser = loginRepository.checkCredentials(em,email,password);
		if(listOfUser==null)
		{
			return false;
		}else{
		return false;
		}
	}

}
