package com.question.service;

import java.util.List;
import java.util.Map;

import com.question.entity.Dept;
import com.question.entity.Statistical;
import com.question.entity.Topic;
/**
 * 
 * @author 管纪伟
 *
 */
public interface StatisticalServiceI {
	/**
	 * 根据不同部门进行统计
	 * @param deptId 部门ID
	 * @param questionnaireId问卷ID
	 * @param topicLen 问题总长度
	 * @return 
	 */
	public String statDept(Integer deptId,Integer questionnaireId);
	/**
	 * 直接统计所有成员
	 * @param questionnaireId
	 * @return
	 */
	public String statAll(Integer questionnaireId);
	/**
	 * 根据部门 、问卷--获取统计数据：题，选项,占有率
	 * @param deptId部门ID
	 * @param questionnaireId问卷ID
	 * @return
	 */
	public Map<Object,Object> getByDeptQuestion(Integer deptId,Integer questionnaireId);
	/**
	 * 获取统计的方法
	 * 通过 问卷，问题，部门的ID获取单个对象数据
	 * @param questionnaireId
	 * @param topicId
	 * @param deptId
	 * @return
	 */
	public Statistical getByQuesTopicDept(Integer questionnaireId,Integer topicId,Integer deptId);
	
	public Map<Object,Object> getDept(Integer questionnaireId,Integer topicId,Integer deptId);
	/**
	 * 根据问卷ID：直接获取全部占有率
	 * @param questionnaireId问卷ID
	 * @return
	 */
	public Map<Object,Object> getAllStat(Integer questionnaireId);
	/**
	 * 获取某问卷的所有题目ID
	 * @param questionnaireId
	 * @return
	 */
	public List<Topic> getTopicSumByDeptID(Integer questionnaireId);
	/**
	 * 根据问卷id 和问题ID获取所有部门
	 * @param questionnaireId
	 * @param topicId
	 * @return
	 */
	public List<Statistical> getDeptSumByQuesTopic(Integer questionnaireId,Integer topicId);
	
}
