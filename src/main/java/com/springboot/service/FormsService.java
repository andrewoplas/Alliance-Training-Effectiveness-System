package com.springboot.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.springboot.body.Question;
import com.springboot.body.QuestionOption;
import com.springboot.body.SkillsAssessment;
import com.springboot.controller.PDFComponent;
import com.springboot.entities.Form;
import com.springboot.entities.FormAnswer;
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
	private FormsService formsService;
	
	@Autowired
	private TrainingPlanService tpService;
	
	@Autowired
	private PDFComponent pdf;
			
	
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

	public boolean insertQuestions(Question[] questions, String formID) {
		int formid;
		
		try {
			formid = Integer.parseInt(formID);
		} catch (NumberFormatException ex) {
			return false;
		}
		
		Form form = new Form(formid);
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
		formsRepository.deleteQuestions(em, formid, questionIDS);
		
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
		
		return true;
	}

	public Form retrieveForm(int formID) {
		return formsRepository.retrieveForm(em, formID);
	}

	public boolean assignForm(String formID, String userEventID) {
		try {
			FormAssignment assignment = new FormAssignment();
			assignment.setForm(new Form(Integer.parseInt(formID)));
			assignment.setUserEvent(new UserEvent(Integer.parseInt(userEventID)));
			assignment.setStatus("unanswered");
			
			formsRepository.insertFormAssignment(em, assignment);
			
			return true;
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean releaseForm(String formID, TrainingPlan training) {
		List<UserEvent> participants = tpService.retrieveTrainingUserEvent(training, "Participant", false);
		
		try {
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
			
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	public SaAssignment retrieveAssignmentById(String assignmentID) {
		try {
			return formsRepository.retrieveAssignment(em, Integer.parseInt(assignmentID));
		} catch (NumberFormatException ex) {
			return null;
		}
	}

	public FormAnswer retrieveFormAnswer(String answerID) {
		FormAnswer formAnswer = null;
		
		try {
			formAnswer = formsRepository.retrieveFormAnswer(em, Integer.parseInt(answerID));
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
		}
		
		return formAnswer;
	}

	public FormAssignment retrieveFormAssignment(String assignmentID) {
		FormAssignment formAnswer = null;
		
		try {
			formAnswer = formsRepository.retrieveFormAssignment(em, Integer.parseInt(assignmentID));
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
		}
		
		return formAnswer;
	}

	public boolean downloadExcel(String formid, String fileName, String trainingPlanID, HttpServletResponse response) {
		int formID = Integer.parseInt(formid);
		Form form = formsService.retrieveForm(formID);
    	TrainingPlan training = tpService.retrieveTraining(trainingPlanID);
    	
    	response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\"");
    	response.setContentType("application/vnd.ms-excel");
    	 
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(form.getDescription());
        sheet.setDefaultColumnWidth(30);
         
        // Styles
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setColor(HSSFColor.BLACK.index);
        font.setBold(true);
        style.setFont(font);
        //to enable newlines you need set a cell styles with wrap=true
        CellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);
         
        // Header Row
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Name");
        header.getCell(0).setCellStyle(style);
        
        List<FormQuestion> questions = form.getFormQuestions();
        int headerCount = 1;
        for (FormQuestion question : questions) {
        	header.createCell(headerCount).setCellValue(question.getDescription());
            header.getCell(headerCount).setCellStyle(style);
            headerCount++;
        }
        
        List<UserEvent> users = training.getUserEvents();
        int rowCount = 1;
        int colCount = 1;
         
        for (UserEvent user : users) {
        	FormAssignment assignment = user.getFormAssignment(formID);
        	
        	// Only answered assignment
        	if(assignment == null) continue;
        	// Set the data
        	List<FormAnswer> answers = assignment.getFormAnswers();
        	XSSFRow aRow = sheet.createRow(rowCount++);
        	aRow.createCell(0).setCellValue(user.getUser().getName());
        	colCount = 1;
        	for(FormAnswer answer : answers) {
        		XSSFCell cell = aRow.createCell(colCount++, CellType.STRING);
        		
        		String type = answer.getFormQuestion().getType(); 
        		if(type.equals("textbox")) {
        			cell.setCellValue(answer.getDescription());
        		} else if(type.equals("radiobutton")) {
    				for(FormOption option : answer.getFormQuestion().getFormOptions()) {
    					if(answer.getDescription().equals(option.getId() + "")) {
    						cell.setCellValue(option.getDescription());
    						break;
    					}
    				}
    			} else if(type.equals("checkbox")) {
    				StringBuilder sb = new StringBuilder();
        			List<String> answerOptions = Arrays.asList(answer.getDescription().split(","));
    				for(FormOption option : answer.getFormQuestion().getFormOptions()) {
    					if(answerOptions.contains(option.getId() + "")) {
    						sb.append(option.getDescription() + "\n");
    					}
    				}
    				cell.setCellValue(sb.toString());
    				cell.setCellStyle(cs);
				} else if(type.equals("scale")) {
					cell.setCellValue(getScaleOption(answer.getDescription()));
				}
        	}
        }
         
       // Output to be downloadable in browser
        try {
        	OutputStream output = response.getOutputStream();
            workbook.write(output);
            workbook.close();
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
        return true;
	}
	
	public boolean generatePDF(HttpServletResponse response, String assignmentID, String fileName) {
		FormAssignment assignment = retrieveFormAssignment(assignmentID);
		
		if(assignment == null) {
			return false;
		}
		
		Form form = assignment.getForm();
		List<FormAnswer> answers = assignment.getFormAnswers();
		
		try {
			Map<String, Object> data = new HashMap<String, Object>();
				data.put("form", form);
				data.put("assignment", assignment);
				data.put("answers", answers);
				
			File file =	pdf.createPdf("form", fileName, data);
			InputStream in = new FileInputStream(file);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
			response.setHeader("Content-Length", String.valueOf(file.length()));
			FileCopyUtils.copy(in, response.getOutputStream());
			
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	
	// Helper Function	
	protected String getScaleOption(String optionNumber) {
		String optionDescription = null;
		
		switch(optionNumber) {
			case "1" : optionDescription = "Strongly Agree"; break;
			case "2" : optionDescription = "Agree"; break;
			case "3" : optionDescription = "Neutral"; break;
			case "4" : optionDescription = "Disagree"; break;
			case "5" : optionDescription = "Strongly Disagree"; break;
		}
		
		return optionDescription;
	}

	public boolean checkAssignmentOwnership(int id, String assignmentID) {
		boolean owns = false;
		
		try {
			FormAssignment assignment = formsRepository.retrieveFormAssignment(em, Integer.parseInt(assignmentID)); 
			if(assignment != null && assignment.getUserEvent().getUser().getId() == id) {
				owns = true;
			}
		} catch (NumberFormatException ex)  {
			ex.printStackTrace();
			
			owns = false;
		}
		
		return owns;
	}

	public boolean checkAssignmentOwnershipByUE(int id, String userEventID) {
		boolean owns = false;
		
		try {
			UserEvent userEvent = tpService.retrieveUserEventById(Integer.parseInt(userEventID)); 
			if(userEvent != null && userEvent.getUser().getId() == id) {
				owns = true;
			}
		} catch (NumberFormatException ex)  {
			ex.printStackTrace();
			
			owns = false;
		}
		
		return owns;
	}
}
