package com.springboot.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailSender")
public class EmailService {

		@Autowired
		private JavaMailSender javaMailSender;
		
		public void send(String sendee, String subject, String body) throws MessagingException
		{
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper;
			
		     helper = new MimeMessageHelper(message,true);
		     helper.setFrom("marccolina456@gmail.com");
		     helper.setTo(sendee);
		     helper.setSubject(subject);
		     helper.setText(body, true);
		     
		     javaMailSender.send(message);
		}
}
