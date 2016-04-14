package com.question.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.question.dao.QuestionnaireDao;
import com.question.dao.TopicDao;
import com.question.entity.Questionnaire;
import com.question.entity.Topic;
@Transactional
@Repository("questionnaireDao")
public class QuestionnaireImpl extends BaseDaoImpl<Questionnaire> implements QuestionnaireDao {

	
}
