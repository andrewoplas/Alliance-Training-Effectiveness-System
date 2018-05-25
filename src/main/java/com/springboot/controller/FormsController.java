package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.body.SkillsAssessment;
import com.springboot.entities.SaCategory;
import com.springboot.service.FormsService;


@Controller
@RequestMapping("/ates/forms")
public class FormsController {
	
	@Autowired
	FormsService formsService;

	@RequestMapping(value = "/skills-assessment")
	public String skillsAssessment(ModelMap map, HttpServletRequest request) {
		List<SaCategory> parents = formsService.getParentCategories();

		map.addAttribute("categories", parents);
		
		return "/forms/skillsAssessment";
	}
	
	@RequestMapping(value = "/skills-assessment", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertSkillsAssessment(@RequestBody SkillsAssessment[] sa, HttpServletRequest request) {		
		formsService.insertSkillsAssessment(sa);
		
		return ResponseEntity.ok(true);
	}
	
}
