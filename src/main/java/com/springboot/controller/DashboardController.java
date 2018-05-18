package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ates")
public class DashboardController {
		
	@RequestMapping(value = "/dashboard")
	public String index() {
		return "dashboard";
	}
}