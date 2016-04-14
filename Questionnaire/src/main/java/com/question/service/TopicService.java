package com.question.service;

import java.util.List;

import com.question.entity.Topic;

public interface TopicService {
	//shu ru fa bu jian le,yi hou hui ba zhu shi bu shang 
	public int createTopic(Topic t);
	
	public void updateTopic(Topic t);
	
	public void deleteTopic(int tid);
	
	public List<Topic> getByQid(int qid);
}
