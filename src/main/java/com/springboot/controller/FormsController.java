package com.springboot.controller;

import java.util.ArrayList;
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
import com.springboot.entities.Questions;
import com.springboot.entities.SaCategory;
import com.springboot.service.FormsService;


@Controller
@RequestMapping("/ates/forms")
public class FormsController {
	
	@Autowired
	FormsService formsService;
	
	static List<Questions> questionMC;
	static List<Questions> question;

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
	
	
	@RequestMapping(value = "/training-effectiveness-evaluation")
	public String LoadtrainingEffectivenessEvaluation(ModelMap map, HttpServletRequest request) {
	
		questionMC=formsService.retrieveMCquestions("MCtea");
		question =formsService.retrieveMCquestions("tea");
		

	System.out.println(questionMC.get(0).getQuestionId() + "KAKAKAKAAKAKA");
	System.out.println(questionMC.get(1).getQuestionId()  + "KAKAKAKAAKAKA");
	System.out.println(questionMC.get(2).getQuestionId()  + "KAKAKAKAAKAKA");
	
		map.addAttribute("listMC", questionMC);
		map.addAttribute("list", question);
		 
		
		
		return "pages/TEA";
	}
	
	
	@RequestMapping(value = "/training-effectiveness-evaluation",method = RequestMethod.POST)
	public String SaveTEAanswers(ModelMap map, HttpServletRequest request) {
	
		String[] answersMC = new String[questionMC.size()];
		for(int counter=0;counter<questionMC.size();counter++)
		{
			answersMC[counter] = request.getParameter(questionMC.get(0).getQuestionId());
			System.out.println(answersMC[counter] + " : " + questionMC.get(0).getQuestionId() + " KAKAKAAKAKAKAKA");
		}
	
		return "pages/TEA";
	}
	
	
	
	
	
}
