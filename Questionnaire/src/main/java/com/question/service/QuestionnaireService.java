package com.question.service;

import com.question.entity.Questionnaire;

public interface QuestionnaireService {
	//问卷的保存或更新方法
	public void createOrUpdateQues(Questionnaire q);
	
	//根据问卷id查询一条问卷信息
	public Questionnaire getOneById(int qid);
	
	//保存或问卷的信息
	public int savaQues(Questionnaire q);
}
