package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.body.Question;
import com.springboot.body.SkillsAssessment;
import com.springboot.entities.Form;
import com.springboot.entities.FormAssignment;
import com.springboot.entities.SaAssignment;
import com.springboot.entities.SaCategory;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.UserEvent;
import com.springboot.service.FormsService;
import com.springboot.service.TrainingPlanService;


@Controller
@RequestMapping("/ates/forms")
public class FormsController {
	
	@Autowired
	private FormsService formsService;
	
	@Autowired
	private TrainingPlanService tpService;
	
	
	@RequestMapping(value = "/assignment")
	public String displayFormsAssignment(ModelMap map) {
		// Display table of trainings with forms to assign
		List<TrainingPlan> trainings = tpService.retrieveTrainings();
		
		map.addAttribute("trainings", trainings);
		
		return "/forms/assignment";
	}

	@RequestMapping(value = "/skills-assessment")
	public String displaySkillsAssessment(ModelMap map, HttpServletRequest request) {
		// Display CRUD for Skills Assessment Form
		List<SaCategory> parents = formsService.getParentCategories();

		map.addAttribute("categories", parents);
		
		return "/forms/skillsAssessment";
	}
	
	@RequestMapping(value = "/skills-assessment", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertSkillsAssessment(@RequestBody SkillsAssessment[] sa, HttpServletRequest request) {
		// Request to Save the Skills Assessment Form
		boolean result = formsService.insertSkillsAssessment(sa);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/skills-assessment/view/{trainingPlanID}")
	public String displayTableAnsweredSkillsAssessment(ModelMap map, @PathVariable String trainingPlanID) {
		// Display table of users' answered skills assessment form
		TrainingPlan training = tpService.retrieveTraining(trainingPlanID);
		
		if(training != null) {
			List<UserEvent> participants = tpService.retrieveTrainingUserEvent(training, "Participant", false);
			
			map.addAttribute("training", training);
			map.addAttribute("userEvents", participants);
		} else {
			return "redirect:/error/404";
		}
		
		return "/forms/skillsAssessment/view";
	}
	
	@RequestMapping(value = "/skills-assessment/view/answer/{assignmentID}")
	public String viewAnsweredSkillsAssessment(@PathVariable String assignmentID, ModelMap map) {
		// Display the answered form of skills assessment
		SaAssignment assignment = formsService.retrieveAssignmentById(assignmentID);
		
		if(assignment != null && assignment.getStatus().equals("answered")) {
			List<SaCategory> parents = formsService.getParentCategories();

			map.addAttribute("categories", parents);
			map.addAttribute("assignment", assignment);
		} else {
			return "redirect:/error/404";
		}
		
		return "/forms/skills-assessment/view/single";
	}
	
	@RequestMapping(value = "/course-feedback")
	public String courseFeedback(ModelMap map) {
		// Display CRUD for Course Feedback Form
		Form form = formsService.retrieveForm(1);
		
		map.addAttribute("form", form);
		map.addAttribute("questions", form.getFormQuestions());
		
		return "/forms/formConfig";
	}
	
	@RequestMapping(value = "/course-feedback/view/{trainingPlanID}")
	public String viewReleaseCourseFeedback(ModelMap map, @PathVariable String trainingPlanID) {
		// Display table of users that is ready to be assigned
		TrainingPlan training = tpService.retrieveTraining(trainingPlanID);
		
		if(training != null) {
			List<UserEvent> participants = tpService.retrieveTrainingUserEvent(training, "Participant", false);
			Form form = formsService.retrieveForm(1);
			
			map.addAttribute("form", form);
			map.addAttribute("questions", form.getFormQuestions());
			map.addAttribute("training", training);
			map.addAttribute("userEvents", participants);
		} else {
			return "redirect:/error/404";
		}
		
		return "/forms/formsView";
	}
	
	@RequestMapping(value = "/facilitator-feedback")
	public String facilitatorFeedback(ModelMap map) {
		// Display CRUD for Facilitator's Feedback Form
		Form form = formsService.retrieveForm(2);
		
		map.addAttribute("form", form);
		map.addAttribute("questions", form.getFormQuestions());
		
		return "/forms/formConfig";
	}
	
	@RequestMapping(value = "/facilitator-feedback/view/{trainingPlanID}")
	public String viewReleaseFacilitatorFeedback(ModelMap map, @PathVariable String trainingPlanID) {
		// Display table of users that is ready to be assigned
		TrainingPlan training = tpService.retrieveTraining(trainingPlanID);
		
		if(training != null) {
			List<UserEvent> participants = tpService.retrieveTrainingUserEvent(training, "Participant", false);
			Form form = formsService.retrieveForm(2);
			
			map.addAttribute("form", form);
			map.addAttribute("questions", form.getFormQuestions());
			map.addAttribute("training", training);
			map.addAttribute("userEvents", participants);
		} else {
			return "redirect:/error/404";
		}
		
		return "/forms/formsView";
	}
	
	@RequestMapping(value = "/training-effectivess-assessment")
	public String trainingEffectivenessAssessment(ModelMap map) {
		// Display CRUD for Training Effectiveness Assessment Form
		Form form = formsService.retrieveForm(3);
		
		map.addAttribute("form", form);
		map.addAttribute("questions", form.getFormQuestions());
		
		return "/forms/formConfig";
	}
	
	@RequestMapping(value = "/training-effectiveness-assessment/view/{trainingPlanID}")
	public String viewReleaseTrainingEffectivenessAssessment(ModelMap map, @PathVariable String trainingPlanID) {
		// Display table of users that is ready to be assigned
		TrainingPlan training = tpService.retrieveTraining(trainingPlanID);
		
		if(training != null) {
			List<UserEvent> participants = tpService.retrieveTrainingUserEvent(training, "Participant", false);
			Form form = formsService.retrieveForm(3);
			
			map.addAttribute("form", form);
			map.addAttribute("questions", form.getFormQuestions());
			map.addAttribute("training", training);
			map.addAttribute("userEvents", participants);
		} else {
			return "redirect:/error/404";
		}
		
		return "/forms/formsView";
	}
	
	@RequestMapping(value = "/training-needs-assessment")
	public String trainingNeedsAssessment(ModelMap map) {
		// Display CRUD for Training Needs Assessment
		Form form = formsService.retrieveForm(4);
		
		map.addAttribute("form", form);
		map.addAttribute("questions", form.getFormQuestions());
		
		return "/forms/formConfig";
	}
	
	@RequestMapping(value = "/training-needs-assessment/view/{trainingPlanID}")
	public String viewReleaseTrainingNeedsAssessment(ModelMap map, @PathVariable String trainingPlanID) {
		// Display table of users that is ready to be assigned
		TrainingPlan training = tpService.retrieveTraining(trainingPlanID);
		
		if(training != null) {
			List<UserEvent> participants = tpService.retrieveTrainingUserEvent(training, "Participant", false);
			Form form = formsService.retrieveForm(4);
			
			map.addAttribute("form", form);
			map.addAttribute("questions", form.getFormQuestions());
			map.addAttribute("training", training);
			map.addAttribute("userEvents", participants);
		} else {
			return "redirect:/error/404";
		}
		
		return "/forms/formsView";
	}
	
	@RequestMapping(value = "/questions/{formID}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> insertQuestions(@PathVariable String formID, @RequestBody Question[] questions) {		
		// Request to insert questions from Forms' CRUD page
		boolean result = formsService.insertQuestions(questions, formID);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/assign", method = RequestMethod.POST)
	public ResponseEntity<?> assignForm(HttpServletRequest request) {
		// Request to assign Form to a user
		String formID = request.getParameter("formID");
		String userEventID = request.getParameter("userEventID");
		
		boolean result = formsService.assignForm(formID, userEventID);
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/assign/all", method = RequestMethod.POST)
	public ResponseEntity<?> releaseForm(HttpServletRequest request) {
		// Request to release (assign) Form to all users 
		String formID = request.getParameter("formID");
		String trainingPlanID = request.getParameter("trainingPlanID");
		
		boolean result = formsService.releaseForm(formID, tpService.retrieveTraining(trainingPlanID));
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/view/answer/{assignmentID}")
	public String viewAnsweredForm(ModelMap map, @PathVariable String assignmentID) {
		// Display the answered Form of user
		FormAssignment assignment = formsService.retrieveFormAssignment(assignmentID);
		
		if(assignment != null) {
			map.addAttribute("assignment", assignment);
			map.addAttribute("form", assignment.getForm());
			map.addAttribute("answers", assignment.getFormAnswers());			
		} else {
			return "redirect:/error/404";
		}
		
		return "/forms/answer";
	}
	
    @RequestMapping(value = "/download/{formID}/{fileName}/{trainingPlanID}", method = RequestMethod.GET)
    public ResponseEntity<?> downloadExcel(HttpServletResponse response, @PathVariable String formID, @PathVariable String fileName, @PathVariable String trainingPlanID) {
    	// Request to Generate an excel file for Forms' answers (Per training)
    	boolean result = formsService.downloadExcel(formID, fileName, trainingPlanID, response);        
    	
        return ResponseEntity.ok(result);
    }
    
    @RequestMapping(value = "/downloadPDF/{assignmentID}/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<?> PDFDownload(HttpServletResponse response, @PathVariable String assignmentID, @PathVariable String fileName) {
    	// Request to convert answered form into PDF
    	boolean result = formsService.generatePDF(response, assignmentID, fileName);
    	
    	return ResponseEntity.ok(result);
    }	
}
