package com.question.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.question.dao.AnSheetDao;
import com.question.dao.DeptDao;
import com.question.dao.QuestionnaireDao;
import com.question.dao.StatisticalDao;
import com.question.dao.TopicDao;
import com.question.entity.Dept;
import com.question.entity.Statistical;
import com.question.entity.Topic;
import com.question.service.StatisticalServiceI;

@Service("statistical")
public class StatisticalServiceImp implements StatisticalServiceI {
	static final char[] topicS = new char[] { 'a', 'b', 'c', 'd', 'e', 'f' };

	// 注入持久化曾对象
	@Resource
	private StatisticalDao statisticalDao;
	@Resource
	private AnSheetDao anSheetlDao;
	@Resource
	private TopicDao topicDao;
	@Resource
	private QuestionnaireDao questionnaireDao;
	@Resource
	private DeptDao deptDao;

	@Override
	public String statDept(Integer deptId, Integer questionnaireId) {
		int topicLen = 0;
		// 同过部门ID获取所有该部门人及每个题的答题情况，涉及到搜索答题表，
		// 情况1：当前部门范围内，某道题的答题情况。将相应的选项取出，看占总部门对象的百分数。
		// 该部门该问卷所有题的个数，通过问卷找题
		List<Topic> sumTopic = getTopicSumByDeptID(questionnaireId);
		int topicSum = sumTopic.size();
		System.out.println(topicSum);

		// 获取某部门，答题人的总个数-------
		List<Integer> topicInPerson = getInPerson(deptId, questionnaireId, sumTopic.get(1).getId());
		float topicPersonSum = topicInPerson.size();
		// 获取单个题存在数量后占有的单问题百分数,注意数据类型都是float，使用long没有小数位，结果会都是0
		float percent = 0;
		Map<Object, Float> topS_pre = null;

		if (topicPersonSum == 0 || topicSum == 0) {
			return null;
		}
		for (int i = 0; i < topicSum; i++) {// 从几个题中查询
			topicLen = sumTopic.get(i).getTopicLen();// 选项的个数
			topS_pre = new HashMap<Object, Float>();
			for (int j = 0; j < topicLen; j++) {
				String s_hql = "select count(*) from AnSheet a where a.deptId=" + deptId + " and a.topicId=" + sumTopic.get(i).getId()
						+ " and a.content like '%" + topicS[j] + "%'";
				float p = anSheetlDao.getCount(s_hql);
				percent = (p / topicPersonSum);
				System.out.println(percent);
				// 放入选项-百分
				topS_pre.put(topicS[j], percent);
			}
			// 直接存入统计表里面
			String hql = "from Statistical s where s.topicId=" + sumTopic.get(i).getId() + " and s.deptId=" + deptId + " and s.questionnaireId = "
					+ questionnaireId;

			Statistical statistical = statisticalDao.getOneByHql(hql);

			if (statistical == null) {
				statistical = new Statistical();
			}
			statistical.setTopicId(sumTopic.get(i).getId());
			statistical.setAPercent(topS_pre.get(topicS[0]));
			statistical.setBPercent(topS_pre.get(topicS[1]));
			statistical.setCPercent(topS_pre.get(topicS[2]));
			statistical.setDPercent(topS_pre.get(topicS[3]));
			statistical.setEPercent(topS_pre.get(topicS[4]));
			statistical.setFPercent(topS_pre.get(topicS[5]));

			statistical.setDeptId(deptId);
			statistical.setQuestionnaireId(questionnaireId);
			// 从map中取出放入统计表，某道题的某选项的占有率,先试用直接写入，后采用map对应(也可以直接输用hibernate语句对应)
			statisticalDao.saveOrUpdate(statistical);
		}
		return null;
	}

	@Override
	public String statAll(Integer questionnaireId) {
		int topicLen = 0;
		// 同过部门ID获取所有该部门人及每个题的答题情况，涉及到搜索答题表，
		// 情况1：当前部门范围内，某道题的答题情况。将相应的选项取出，看占总部门对象的百分数。
		// 该部门该问卷所有题的个数，通过问卷找题
		List<Topic> sumTopic = getTopicSumByDeptID(questionnaireId);
		int topicSum = sumTopic.size();
		System.out.println(topicSum);

		// 获取某部门，答题人的总个数-------
		Integer topicPersonSum = getQestionPerson(questionnaireId);
		// 获取单个题存在数量后占有的单问题百分数,注意数据类型都是float，使用long没有小数位，结果会都是0
		float percent = 0;
		Map<Object, Float> topS_pre = null;

		if (topicPersonSum == 0 || topicSum == 0) {
			return null;
		}
		for (int i = 0; i < topicSum; i++) {// 从几个题中查询
			topicLen = sumTopic.get(i).getTopicLen();// 选项的个数
			topS_pre = new HashMap<Object, Float>();
			for (int j = 0; j < topicLen; j++) {
				String s_hql = "select count(*) from AnSheet a where a.topicId=" + sumTopic.get(i).getId() + " and a.content like '%" + topicS[j] + "%'";

				float p = anSheetlDao.getCount(s_hql);
				percent = (p / topicPersonSum);
				System.out.println(percent);
				// 放入选项-百分
				topS_pre.put(topicS[j], percent);
			}
			// 直接存入统计表里面
			String hql = "from Statistical s where s.topicId=" + sumTopic.get(i).getId() + " and s.deptId= -1" + " and s.questionnaireId = " + questionnaireId;
			Statistical statistical = statisticalDao.getOneByHql(hql);
			if (statistical == null) {
				statistical = new Statistical();
			}
			statistical.setTopicId(sumTopic.get(i).getId());
			statistical.setAPercent(topS_pre.get(topicS[0]));
			statistical.setBPercent(topS_pre.get(topicS[1]));
			statistical.setCPercent(topS_pre.get(topicS[2]));
			statistical.setDPercent(topS_pre.get(topicS[3]));
			statistical.setEPercent(topS_pre.get(topicS[4]));
			statistical.setFPercent(topS_pre.get(topicS[5]));

			statistical.setDeptId(-1);
			statistical.setQuestionnaireId(questionnaireId);
			// 从map中取出放入统计表，某道题的某选项的占有率,先试用直接写入，后采用map对应(也可以直接输用hibernate语句对应)
			statisticalDao.saveOrUpdate(statistical);
		}
		return null;
	}

	@Override
	public Map<Object, Object> getByDeptQuestion(Integer deptId, Integer questionnaireId) {
		String hql = "from Statistical s where s.deptId = " + deptId + " and s.questionnaireId= " + questionnaireId;
		Map<Object, Object> topicSelect = this.getStat(hql);
		return topicSelect;
	}

	@Override
	public Map<Object, Object> getAllStat(Integer questionnaireId) {
		String hql = "from Statistical s where s.questionnaireId= " + questionnaireId;
		Map<Object, Object> topicSelect = this.getStat(hql);
		return topicSelect;
	}

	public Map<Object, Object> getStat(String hql) {
		float a, b, c, d, e, f = 0;
		Map<Object, Object> topicSelect = new HashMap<Object, Object>();
		Map<Object, Float> selectPercent = new HashMap<Object, Float>();
		List<Statistical> lists = statisticalDao.getMore(hql);
		for (Statistical l : lists) {
			a = l.getAPercent();
			b = l.getBPercent();
			c = l.getCPercent();
			d = l.getDPercent();
			e = l.getEPercent();
			f = l.getFPercent();

			selectPercent.put(topicS[0], a);
			selectPercent.put(topicS[1], b);
			selectPercent.put(topicS[2], c);
			selectPercent.put(topicS[3], d);
			selectPercent.put(topicS[4], e);
			selectPercent.put(topicS[5], f);

			topicSelect.put("topic" + l.getTopicId(), selectPercent);
		}
		return topicSelect;
	}

	/**
	 * 获取所有人数
	 * 
	 * @param deptId
	 * @param questionnaireId
	 * @return
	 */
	public List<Integer> getInPerson(Integer deptId, Integer questionnaireId, Integer topicId) {
		List<Integer> topicIdS = null;
		String Sql = "select an.topic_id from an_sheet an where dept_id=" + deptId + " and questionnaire_id=" + questionnaireId + " and topic_id=" + topicId;
		topicIdS = anSheetlDao.getBySql(Sql);
		return topicIdS;
	}

	public Integer getQestionPerson(Integer questionnaireId) {
		Integer questionsP = null;
		// Questionnaire q = new Questionnaire();
		// q.setId(questionnaireId);
		String hql = "from Questionnaire q where q.id=" + questionnaireId;
		questionsP = questionnaireDao.getOneByHql(hql).getSumnum();
		return questionsP;
	}

	/**
	 * 获取所有参与本问卷的人的ID，
	 * 
	 * @param questionnaireId
	 * @return
	 */
	public List<Integer> getAllPerson(Integer questionnaireId) {
		List<Integer> topicPersonIdS = null;
		String Sql = "select an.topic_id from an_sheet an where questionnaire_id=" + questionnaireId;
		topicPersonIdS = anSheetlDao.getBySql(Sql);
		return topicPersonIdS;
	}

	/**
	 * 获取某问卷的所有题目ID
	 * 
	 * @param questionnaireId
	 * @return
	 */
	@Override
	public List<Topic> getTopicSumByDeptID(Integer questionnaireId) {
		List<Topic> STopic = null;
		String hql = "from Topic t where t.questionnaire=" + questionnaireId;
		STopic = topicDao.getMore(hql);
		return STopic;
	}

	@Override
	public List<Statistical> getDeptSumByQuesTopic(Integer questionnaireId, Integer topicId) {
		List<Statistical> SDept = null;
		String hql = "from Statistical s where s.questionnaireId=" + questionnaireId+" and s.topicId="+topicId;
		SDept = statisticalDao.getMore(hql);
		return SDept;
	}

	@Override
	public Statistical getByQuesTopicDept(Integer questionnaireId, Integer topicId, Integer deptId) {
		// TODO Auto-generated method stub
		Statistical stat = new Statistical();
		String hql = "from Statistical s where s.questionnaireId=" + questionnaireId+" and s.topicId="+topicId+" and s.deptId="+deptId;
		stat = statisticalDao.getOneByHql(hql);
		return stat;
	}

	@Override
	public Map<Object, Object> getDept(Integer questionnaireId, Integer topicId, Integer deptId) {
		String hql = "from Topic t where t.id =" +topicId;
		Statistical stat = this.getByQuesTopicDept(questionnaireId, topicId, deptId);
		Integer topicLen = topicDao.getOneByHql(hql).getTopicLen();
		
		ArrayList<Float> percent = new ArrayList<Float>();
		percent.add(stat.getAPercent());
		percent.add(stat.getBPercent());
		percent.add(stat.getCPercent());
		percent.add(stat.getDPercent());
		percent.add(stat.getEPercent());
		percent.add(stat.getFPercent());
		
		Map<Object, Object> selePer = new HashMap<Object, Object>();
		
		for(int i=0;i<topicLen;i++){
			selePer.put(topicS[i], percent.get(i));
		}
		return selePer;
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("我就测试一下，你别报错啊");
	}
	
}
