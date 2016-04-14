package com.question.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.question.dao.ManageUserDao;
import com.question.dao.QuestionnaireDao;
import com.question.entity.Manageuser;
import com.question.entity.Questionnaire;
import com.question.service.ManageUserService;
@Service("ManageUserImpl")
public class ManageUserIpml implements ManageUserService {
	
	@Resource(name="manageUserDao")
	private ManageUserDao manageUserDao; 
	
	@Resource(name="questionnaireDao")
	private QuestionnaireDao questionnaireDao;
	
	public String checkLogin(Manageuser m, Map<String, Object> map) {
		System.out.println(m.getName());
		String hql="from Manageuser where name='"+m.getName()+"' and pwd='"+m.getPwd()+"'";
		List<Manageuser> list=manageUserDao.getMore(hql);
		if(list.size()>0){
			map.put("userid", list.get(0).getId());
			String ql="";
			//查询已结束的问卷
			List<Questionnaire> qlistyes=new ArrayList<Questionnaire>();
			//查询没有结束的问卷
			List<Questionnaire> qlistno=new ArrayList<Questionnaire>();
			if(list.get(0).getCompetence()==0){
				ql="from Questionnaire where status=4";
				qlistyes=questionnaireDao.getMore(ql);
				ql="from Questionnaire where status!=4";
				qlistno=questionnaireDao.getMore(ql);
				map.put("competent", 0);
			}else{
				ql="from Questionnaire where status=4 and muid="+list.get(0).getId();
				qlistyes=questionnaireDao.getMore(ql);
				ql="from Questionnaire where status!=4 and muid="+list.get(0).getId();
				qlistno=questionnaireDao.getMore(ql);
				map.put("competent", 1);
			}
			map.put("qlistyes", qlistyes);
			map.put("qlistno", qlistno);
			return "QuestionIndex";
		}else{
			map.put("tip", "系统提示：用户名或密码不正确！！！");
			map.put("name",m.getName());
			return "ManagerLogin";
		}
	}
	
}
