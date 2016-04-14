package com.question.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.question.dao.QuestionnaireDao;
import com.question.entity.Questionnaire;
import com.question.service.QuestionnaireService;

@Service("questionnarieService")
public class QuestionnarieImpl implements QuestionnaireService {

	@Resource(name="questionnaireDao")
	private QuestionnaireDao questionnaireDao;
	
	public void createOrUpdateQues(Questionnaire q) {
		questionnaireDao.saveOrUpdate(q);
	}

	public Questionnaire getOneById(int qid) {
		List<Questionnaire> list=questionnaireDao.getMore("from Questionnaire where id="+qid);
		return list.get(0);
	}

	public int savaQues(Questionnaire q) {
		int id=questionnaireDao.save(q);
		return id;
	}

}
