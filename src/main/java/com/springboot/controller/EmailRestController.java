package com.springboot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entities.custom.Mail;
import com.springboot.service.CustomizedEmailService;

@RestController
public class EmailRestController{
	
	@Autowired
	private CustomizedEmailService emailService;
	
	
	public void sendGeneratedPassword(String password, String name, String email) throws MessagingException, IOException
	{
		Mail mail = new Mail();
		mail.setFrom("marccolina456@gmail.com");
		mail.setTo(email);
		mail.setSubject("Alliance Training Effectiveness System Password ");
	
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("generatedPassword", password);
		model.put("name", name);
		mail.setModel(model);
		
		emailService.sendSimpleMessage(mail);
	 }
}

