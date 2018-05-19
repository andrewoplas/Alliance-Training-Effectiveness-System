package com.springboot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.service.CustomizedEmailService;

//@SpringBootApplication
//public class Application implements ApplicationRunner {
//
//    private static Logger log = LoggerFactory.getLogger(Application.class);
//
//    @Autowired
//    private CustomizedEmailService emailService;
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Application.class, args);
//    }
//
//    @Override
//    public void run(ApplicationArguments applicationArguments) throws Exception {
//        log.info("Sending Email with Thymeleaf HTML Template Example");
//
//        Mail mail = new Mail();
//        mail.setFrom("marccolina456@gmail.com");
//        mail.setTo("marccolina456@gmail.com");
//        mail.setSubject("Sending Email with Thymeleaf HTML Template Example GAGA ");
//
//        Map<String, Object> model = new HashMap<String, Object>();
////        Map < String, Object > model = new HashMap < String, Object > ();
//        model.put("name", "Memorynotfound.com");
//        model.put("location", "Philippines");
//        model.put("signature", "MARC GWAPO!");
//        mail.setModel(model);
//
//        emailService.sendSimpleMessage(mail);
//    }
//}


@RestController
public class Application{
	@Autowired
	private CustomizedEmailService emailService;
	
//	@RequestMapping("/send")
	 public void run() throws MessagingException, IOException
	 {
		
		  Mail mail = new Mail();
		  mail.setFrom("marccolina456@gmail.com");
		  mail.setTo("marccolina456@gmail.com");
		  mail.setSubject("Sending Email with Thymeleaf HTML Template Example GAGA ");
		
		  Map<String, Object> model = new HashMap<String, Object>();
		//  Map < String, Object > model = new HashMap < String, Object > ();
		  model.put("name", "Memorynotfound.com");
		  model.put("location", "Philippines");
		  model.put("signature", "MARC GWAPO!");
		  mail.setModel(model);
		
		  emailService.sendSimpleMessage(mail);

	 }
}

