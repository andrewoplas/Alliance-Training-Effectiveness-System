package com.springboot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.body.SkillsAssessment;
import com.springboot.entities.SaCategory;
import com.springboot.entities.User;
import com.springboot.repository.custom.FormsRepository;

@Service
public class FormsService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private FormsRepository formsRepository;
	
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
		
		formsRepository.insertSkillsAssessment(em, categories, categoryIDS);
	}

	public List<SaCategory> getParentCategories() {
		return formsRepository.retrieveParentSkillsAssessment(em);
	}
	
//	public String createHTMLSA(List<SaCategory> categories) {
//		StringBuilder dataHTML = new StringBuilder("<ol class=\"dd-list\">");
//		
//		for(SaCategory category : categories) {
//			dataHTML.append("<li class=\"dd-item dd3-item\" data-id=\"" + category.getId() + "\">");
//			dataHTML.append("<div class=\"dd-handle dd3-handle\"></div>");
//			dataHTML.append("<div class=\"dd3-content\" id=\"id-" + category.getId() + "\"> " + category.getDescription() + "</div>");
//			dataHTML.append("<button type=\"button\" class=\"btn-remove-item btn btn-danger btn-outline btn-circle\">");
//			dataHTML.append("<i class=\"mdi mdi-close\"></i></button>");
//			
//			if(category.getSaCategories().size() > 0) {
//				createHTMLSAAppendChild(dataHTML, category.getSaCategories());
//			}
//			
//			dataHTML.append("</li>");
//		}
//		
//		dataHTML.append("</ol>");
//		
//		return dataHTML.toString();
//	}
//	
//	public void createHTMLSAAppendChild(StringBuilder dataHTML, List<SaCategory> children) {
//		dataHTML.append("<ol class=\"dd-list\">");
//		
//		// Sort
//		Collections.sort(children, new Comparator<SaCategory>() {
//		    @Override
//		    public int compare(SaCategory a, SaCategory b) {
//		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//		        return (a.getRowOrder() > b.getRowOrder()) ? 1 : -1;
//		    }
//		});
//		
//		
//		for(SaCategory child : children) {
//			dataHTML.append("<li class=\"dd-item dd3-item\" data-id=\"" + child.getId() + "\">");
//			dataHTML.append("<div class=\"dd-handle dd3-handle\"></div>");
//			dataHTML.append("<div class=\"dd3-content\" id=\"id-" + child.getId() + "\"> " + child.getDescription() + "</div>");
//			dataHTML.append("<button type=\"button\" class=\"btn-remove-item btn btn-danger btn-outline btn-circle\">");
//			dataHTML.append("<i class=\"mdi mdi-close\"></i></button>");
//			
//			if(child.getSaCategories().size() > 0) {
//				createHTMLSAAppendChild(dataHTML, child.getSaCategories());
//			}
//			
//			dataHTML.append("</li>");
//		}		
//		dataHTML.append("</ol>");
//	}
	
	
}
