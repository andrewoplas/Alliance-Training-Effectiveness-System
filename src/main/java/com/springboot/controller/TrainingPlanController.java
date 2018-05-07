package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("ates/training")
public class TrainingPlanController{
	
	@RequestMapping(value = { "", "/" })
	public String index() {
		return "pages/trainingPlan";
	}
	
	@RequestMapping(value = "/create")
	public String create() {
		return "pages/trainingPlan";
	}
}