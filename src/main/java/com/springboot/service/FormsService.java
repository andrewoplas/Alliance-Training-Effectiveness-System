package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.body.Question;
import com.springboot.body.QuestionOption;
import com.springboot.body.SkillsAssessment;
import com.springboot.entities.Form;
import com.springboot.entities.FormAssignment;
import com.springboot.entities.FormOption;
import com.springboot.entities.FormQuestion;
import com.springboot.entities.SaAssignment;
import com.springboot.entities.SaCategory;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.UserEvent;
import com.springboot.repository.custom.FormsRepository;

@Service
public class FormsService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private FormsRepository formsRepository;

	@Autowired
	TrainingPlanService tpService;
	
	protected void insertSkillsAssessmentChild(SaCategory parent, List<SkillsAssessment> children, ArrayList<Integer> categoryIDS) {
		for(SkillsAssessment child : children) {
			SaCategory childCategory = new SaCategory();
			childCategory.setId(child.getId());
			childCategory.setDescription(child.getContent());
			childCategory.setSaCategory(parent);
			childCategory.setRowOrder(child.getOrder());
			categoryIDS.add(child.getId());
			
			if(child.getChildren() != null && !child.getChildren().isEmpty()) {
				insertSkillsAssessmentChild(childCategory, child.getChildren(), categoryIDS);
			}
			parent.addSaCategory(childCategory);
			//categories.add(childCategory);
		}
	}

	public void insertSkillsAssessment(SkillsAssessment[] sa) {
		List<SaCategory> categories = new ArrayList<SaCategory>();
		ArrayList<Integer> categoryIDS = new ArrayList<Integer>();
		
		for(SkillsAssessment category : sa) {
			SaCategory parent = new SaCategory();
			parent.setId(category.getId());
			parent.setDescription(category.getContent());
			parent.setSaCategory(null);
			parent.setRowOrder(category.getOrder());
			categoryIDS.add(category.getId());
			categories.add(parent);
			
			if(category.getChildren() != null && !category.getChildren().isEmpty()) {
				insertSkillsAssessmentChild(parent, category.getChildren(), categoryIDS);
			}
		}
		
		// Delete removed category
		if(categoryIDS.size() == 0) { categoryIDS.add(0); }
		formsRepository.deleteSkillsAssessment(em, categoryIDS);
		
		// Insert Recursively
		insertSA(categories, null);
	}
	
	public void insertSA(List<SaCategory> categories, SaCategory parent) {
		for(SaCategory category : categories) {
			category.setSaCategory(parent);
			
			if(formsRepository.retrieveCategory(em, category.getId()) != null) {
				// Update category
				formsRepository.updateCategory(em, category);
			} else {
				// Insert category
				category.setId(0);
				category = formsRepository.insertCategory(em, category);
			}
						
			if(!category.getSaCategories().isEmpty()) {
				insertSA(category.getSaCategories(), category);
			}
		}
	}

	public List<SaCategory> getParentCategories() {
		return formsRepository.retrieveParentSkillsAssessment(em);
	}

	public void insertQuestions(Question[] questions, int formID) {
		Form form = new Form(formID);
		List<FormQuestion> formQuestions = new ArrayList<FormQuestion>();
		List<Integer> questionIDS = new ArrayList<Integer>();
		
		for(Question question : questions) {
			FormQuestion formQuestion = new FormQuestion();
			formQuestion.setDescription(question.getQuestion());
			formQuestion.setType(question.getType());
			formQuestion.setForm(form);
			
			int formId = question.getId();
			if(formId > 0) {
				questionIDS.add(formId);
				formQuestion.setId(formId);
			}
			
			QuestionOption[] options = question.getOption();
			if(options.length > 0) {
				for(QuestionOption option : options) {
					FormOption formOption = new FormOption(option.getId());
					formOption.setDescription(option.getDescription());
					
					formQuestion.addFormOption(formOption);
				}
			}
			
			formQuestions.add(formQuestion);
		}
		
		// Delete removed questions
		if(questionIDS.size() == 0) { questionIDS.add(0); }
		formsRepository.deleteQuestions(em, formID, questionIDS);
		
		// Merge or Persist
		for(FormQuestion question : formQuestions) {
			if(question.getId() == 0) {
				// Insert Questions
				question = formsRepository.insertQuestion(em, question);
			} else {
				// Update Questions
				formsRepository.updateQuestion(em, question);
			}
			
			if(question.getFormOptionsCount() > 0) {
				List<FormOption> options = question.getFormOptions();
				
				// Delete removed questions
				formsRepository.deleteOption(em, question.getId(), question.getFormOptionIDS());
				
				for(FormOption option : options) {
					// Insert Options
					option.setFormQuestion(question);
					formsRepository.mergeOptions(em, option);
				}
			}
		}
	}

	public Form retrieveForm(int formID) {
		return formsRepository.retrieveForm(em, formID);
	}

	public void assignForm(String formID, String userEventID) {
		try {
			FormAssignment assignment = new FormAssignment();
			assignment.setForm(new Form(Integer.parseInt(formID)));
			assignment.setUserEvent(new UserEvent(Integer.parseInt(userEventID)));
			assignment.setStatus("unanswered");
			
			formsRepository.insertFormAssignment(em, assignment);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
	}

	public void releaseForm(String formID, TrainingPlan training) {
		List<UserEvent> participants = tpService.retrieveTrainingUserEvent(training, "Participant", false);
		int form = Integer.parseInt(formID);
		
		for(UserEvent participant : participants) {
			if(!formsRepository.containsFormAssignment(em, form, participant.getId())) {
				FormAssignment assignment = new FormAssignment();
				assignment.setForm(new Form(form));
				assignment.setUserEvent(new UserEvent(participant.getId()));
				assignment.setStatus("unanswered");
				
				formsRepository.insertFormAssignment(em, assignment);
			}
		}
	}
	
	public SaAssignment retrieveAssignmentById(int assignmentID) {
		return formsRepository.retrieveAssignment(em, assignmentID);
	}
	
}
