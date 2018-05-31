package com.springboot.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.springboot.controller.EmailRestController;
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
	
	@Autowired
	EmailRestController application;
	
	
	public List<CustomUser> retrieveUsers() {
		// Get users and converted to CustomUser entity
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
			usersRepository.removeUser(em, Integer.parseInt(id));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void approveUser(String id) {
		try {
			usersRepository.approveUser(em, Integer.parseInt(id));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void declineUser(String id) {
		try {
			usersRepository.declineUser(em, Integer.parseInt(id));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String insertUser(String name, String email, String position) throws MessagingException, IOException {
		String result = "success";
		if(registerRepository.contains(em, email)) {
			result = "email_exists";
		} else {
			String password = getRandomPassword();
			String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
			
			User user = new User();
			Position pos = new Position();
			
			try {
				pos.setId(Integer.parseInt(position));
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				return "error";
			}
			
			user.setName(name);
			user.setPosition(pos);
			user.setEmail(email);
			user.setPassword(hashedPassword);
			user.setStatus("approved");
			user.setUserEvents(null);
			user.setAttendances(null);
			
			// Send Password to Email
			application.sendGeneratedPassword(password, name, email);
			
			registerRepository.insertUser(em, user);
		}
		
		return result;
	}
	
	public String editUser(String id, String name, String email, String position, String password) throws MessagingException, IOException {
		String result = "success";
		
		if(usersRepository.containsByIdAndString(em, email, id)) {
			result = "email_exists";
		} else {
			User user = new User();
			Position pos = new Position();
			
			try {
				user.setId(Integer.parseInt(id));
				user.setName(name);
					pos.setId(Integer.parseInt(position));
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				
				return "error";
			}
			user.setPosition(pos);
			user.setEmail(email);
			
			// Edit password if checkbox is checked
			if(password != null && password.equals("on")) {
				String generatedPassword = getRandomPassword();
				String hashedPassword = Hashing.sha256().hashString(generatedPassword, StandardCharsets.UTF_8).toString();
				user.setPassword(hashedPassword);
				
				// Send Password to Email
				application.sendGeneratedPassword(generatedPassword, name, email);
			} 
			
			usersRepository.editUser(em, user);
		}
		
		return result;
	}
	
	public User retrieveUser(String id) {
		return usersRepository.retrieveUser(em, id);
	}
	
	public void editUser(String id, String name, String position, String password) {		
		User user = new User(Integer.parseInt(id));
		Position pos = new Position();
		user.setName(name);
			
		try {
			pos.setId(Integer.parseInt(position));
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			
			return;
		}
		user.setPosition(pos);		
		
		if(password != null && !password.isEmpty()) {
			user.setPassword(Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
		} 
		
		usersRepository.editUserByView(em, user);
	}
	
	
	// Helper Function
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
