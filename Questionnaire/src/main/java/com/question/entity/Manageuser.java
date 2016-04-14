package com.question.entity;

/**
 * Manageuser entity. @author MyEclipse Persistence Tools
 */
//后台用户管理实体类
public class Manageuser{
	
	private Integer id;
	private String name;
	private String pwd;
	private Integer competence;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getCompetence() {
		return this.competence;
	}

	public void setCompetence(Integer competence) {
		this.competence = competence;
	}

}