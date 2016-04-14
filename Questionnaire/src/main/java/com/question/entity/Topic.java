package com.question.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Topic entity. @author MyEclipse Persistence Tools
 */

public class Topic implements Serializable{

	// Fields

	private Integer id;
	private Questionnaire questionnaire;
	private String title;
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private Integer type;
	private Integer topicLen;
	// Constructors

	

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}
	
	@JsonIgnore
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getA() {
		return this.a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return this.b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return this.c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return this.d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getE() {
		return this.e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getF() {
		return this.f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTopicLen() {
		return topicLen;
	}

	public void setTopicLen(Integer topicLen) {
		this.topicLen = topicLen;
	}
	
}