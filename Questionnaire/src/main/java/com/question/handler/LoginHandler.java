package com.question.handler;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.question.entity.Manageuser;
import com.question.service.ManageUserService;
/**
 * 这个类是对所有的登录所做的类
 * 所有的登陆的方法，设置，权限的划分等都写在这个类里面
 */
@Controller
public class LoginHandler {
	
	/*@Autowired
	@Qualifier("ManageUserDao")
	private BaseDao<Manageuser> manageUserDao;
	
	@Autowired
	@Qualifier("QuestionnaireDao")
	private BaseDao<Questionnaire> questionnaireDao;*/
	
	@Autowired
	@Qualifier("ManageUserImpl")
	private ManageUserService manageUserService;
	
	/**
	 * 此方法是对后台问卷发布者以及超级管理员的登录方法
	 * 目前所编辑的版本是第一个版本所以没有涉及到登陆的同步性
	 * 也没有涉及到只能允许一个账号在线只能有一个登录
	 * 这些问题在第二个版本中会实现
	 */
	@RequestMapping("loginCheck")
	public String loginCheck(@Valid Manageuser m,Map<String,Object> map){
		return	manageUserService.checkLogin(m, map);
	}
	
	@RequestMapping("login")
	public String login(){
		return "ManagerLogin";
	}
}
