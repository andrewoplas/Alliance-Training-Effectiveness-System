package com.springboot.body;

import java.util.List;

public class SkillsAssessment {
	private int id;
	private int order;
	private String content;
	private List<SkillsAssessment> children;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<SkillsAssessment> getChildren() {
		return children;
	}
	public void setChildren(List<SkillsAssessment> children) {
		this.children = children;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}
