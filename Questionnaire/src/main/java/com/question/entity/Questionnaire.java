package com.question.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Questionnaire entity. @author MyEclipse Persistence Tools
 */

public class Questionnaire implements Serializable{

	// Fields

	private Integer id;
	private Integer muid;
	private String number;
	private String title;
	private String brief;
	private Integer status;
	private Integer anonymous;
	private Date cratetime;
	private Date begintime;
	private Date stoptime;
	private Integer sumnum;
	// Constructors

	/** default constructor */
	



	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMuid() {
		return this.muid;
	}

	public void setMuid(Integer muid) {
		this.muid = muid;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAnonymous() {
		return this.anonymous;
	}

	public void setAnonymous(Integer anonymous) {
		this.anonymous = anonymous;
	}

	public Date getCratetime() {
		return this.cratetime;
	}

	public void setCratetime(Date cratetime) {
		this.cratetime = cratetime;
	}

	public Date getBegintime() {
		return this.begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getStoptime() {
		return this.stoptime;
	}

	public void setStoptime(Date stoptime) {
		this.stoptime = stoptime;
	}

	public Integer getSumnum() {
		return this.sumnum;
	}

	public void setSumnum(Integer sumnum) {
		this.sumnum = sumnum;
	}


}