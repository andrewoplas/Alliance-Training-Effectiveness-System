package com.springboot.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.service.EmailService;

@RestController
@RequestMapping("/send")
public class EMAILCONTROLLER {

	

		@Autowired
		EmailService emailSender;
		
		@RequestMapping("/sendEmail")
		public String  sendmail() throws MessagingException
		{
			emailSender.send("marccolina456@gmail.com","EMAIL","GWAPO KAYKA MARC, PRAMIS!!");
			
			return "email";
		}

}
