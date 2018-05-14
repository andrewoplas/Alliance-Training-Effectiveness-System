package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.Position;
import com.springboot.entities.User;
import com.springboot.entities.custom.CustomUser;
import com.springboot.service.UsersService;

@Controller
@RequestMapping("/ates/users")
public class PositionController {
	
}