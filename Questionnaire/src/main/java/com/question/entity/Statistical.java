package com.question.entity;

/**
 * Statistical entity. @author MyEclipse Persistence Tools
 */

public class Statistical{

	// Fields

	private Integer id;
	private Integer questionnaireId;
	private Integer topicId;
	private Integer deptId;
	private Float APercent;
	private Float BPercent;
	private Float CPercent;
	private Float DPercent;
	private Float EPercent;
	private Float FPercent;

	// Constructors

	/** default constructor */
	

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionnaireId() {
		return this.questionnaireId;
	}

	public void setQuestionnaireId(Integer questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Integer getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Float getAPercent() {
		return this.APercent;
	}

	public void setAPercent(Float APercent) {
		this.APercent = APercent;
	}

	public Float getBPercent() {
		return this.BPercent;
	}

	public void setBPercent(Float BPercent) {
		this.BPercent = BPercent;
	}

	public Float getCPercent() {
		return this.CPercent;
	}

	public void setCPercent(Float CPercent) {
		this.CPercent = CPercent;
	}

	public Float getDPercent() {
		return this.DPercent;
	}

	public void setDPercent(Float DPercent) {
		this.DPercent = DPercent;
	}

	public Float getEPercent() {
		return this.EPercent;
	}

	public void setEPercent(Float EPercent) {
		this.EPercent = EPercent;
	}

	public Float getFPercent() {
		return this.FPercent;
	}

	public void setFPercent(Float FPercent) {
		this.FPercent = FPercent;
	}

}