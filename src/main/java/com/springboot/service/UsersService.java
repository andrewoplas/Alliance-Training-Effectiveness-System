package com.springboot.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.springboot.entities.Position;
import com.springboot.entities.User;
import com.springboot.entities.custom.CustomUser;
import com.springboot.repository.custom.RegisterRepository;
import com.springboot.repository.custom.UsersRepository;

@Service
public class UsersService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private RegisterRepository registerRepository;
	
	
	public List<CustomUser> retrieveUsers() {
		List data = usersRepository.retrieveUsers(em);
		List<CustomUser> cUsers = new ArrayList<CustomUser>();
		List<Integer> track = new ArrayList<Integer>();
		int index = 0;
		
		
		int length = data.size();
		for(int i=0; i<length; i++) {
			CustomUser cUser = new CustomUser();
			
			Object[] row = (Object[]) data.get(i);
			index = track.indexOf(row[0]);
			if(index != -1) {
				cUsers.get(index).setTraining(row[4].toString());
				cUsers.get(index).setRole(row[5].toString());
			} else {
				cUser.setId((int)row[0]);
				cUser.setName(row[1].toString());
				cUser.setPosition(row[2].toString());
				cUser.setEmail(row[3].toString());
				if(row[4] != null)
					cUser.setTraining(row[4].toString());
				if(row[5] != null)
					cUser.setRole(row[5].toString());
				
				cUsers.add(cUser);
				track.add((Integer)row[0]);
			}
		}	
		
		return cUsers;
	}
	
	public List<User> retrieveApprovedUsers() {
		List<User> users = usersRepository.retrieveApprovedUsers(em);
		
		return users;
	}

	public List<User> retrievePendingUsers() {
		List<User> users = usersRepository.retrievePendingUsers(em);
		
		return users;
	}
	
	public void removeUser(String id) {
		try {
			int uid = Integer.parseInt(id);
			usersRepository.removeUser(em, uid);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void approveUser(String id) {
		try {
			int uid = Integer.parseInt(id);
			usersRepository.approveUser(em, uid);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void declineUser(String id) {
		try {
			int uid = Integer.parseInt(id);
			usersRepository.declineUser(em, uid);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String insertUser(String name, String email, String position) {
		String result = "success";
		String password = getRandomPassword();
		if(registerRepository.contains(em, email)) {
			result = "email_exists";
		} else {
			String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
			registerRepository.insertUser(em, name, email, position, hashedPassword, "approved");
		}
		
		return result;
	}
	
	public String editUser(String id, String name, String email, String position, String password) {
		String result = "success";
		
		if(usersRepository.containsByIdAndString(em, email, id)) {
			result = "email_exists";
		} else {
			User user = new User();
			Position pos = new Position();
			user.setId(Integer.parseInt(id));
			user.setName(name);
				pos.setId(Integer.parseInt(position));
			user.setPosition(pos);
			user.setEmail(email);
			
			if(password != null && password.equals("on")) {
				String hashedPassword = Hashing.sha256().hashString(getRandomPassword(), StandardCharsets.UTF_8).toString();
				user.setPassword(hashedPassword);
			} 
			
			usersRepository.editUser(em, user);
		}
		
		return result;
	}
	
	public User retrieveUser(String id) {
		return usersRepository.retrieveUser(em, id);
	}
	
	protected String getRandomPassword() {
        String PasswordChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * PasswordChars.length());
            sb.append(PasswordChars.charAt(index));
        }
        String generatedPassword = sb.toString();
        return generatedPassword;
    }

}
