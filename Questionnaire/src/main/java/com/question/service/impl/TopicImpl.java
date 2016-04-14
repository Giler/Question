package com.question.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.question.dao.TopicDao;
import com.question.entity.Topic;
import com.question.service.TopicService;
@Service("topicService")
public class TopicImpl implements TopicService {
	
	@Resource(name="topicDao")
	private TopicDao topicDao;
	
	//新建问题
	public int createTopic(Topic t) {
		int id=topicDao.save(t);
		return id;
	}

	public void updateTopic(Topic t) {
		topicDao.saveOrUpdate(t);
	}

	public void deleteTopic(int tid) {
		topicDao.delete("delete Topic where id="+tid);
	}

	public List<Topic> getByQid(int qid) {
		List<Topic> topics=topicDao.getMore("from Topic where question_id="+qid);
		return topics;
	}

}
