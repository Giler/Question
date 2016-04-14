package com.question.entity;

/**
 * AnSheet entity. @author MyEclipse Persistence Tools
 */

public class AnSheet{

	// Fields

	private Integer id;
	private Integer userId;
	private Integer topicId;
	private Integer questionnaireId;
	private Integer deptId;
	private String content;

	// Constructors

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getQuestionnaireId() {
		return this.questionnaireId;
	}

	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}