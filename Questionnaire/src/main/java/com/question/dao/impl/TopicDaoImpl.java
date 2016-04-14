package com.question.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.question.dao.TopicDao;
import com.question.entity.Topic;
@Transactional
@Repository("topicDao")
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao {

	
}
