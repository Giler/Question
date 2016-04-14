package com.Question.test;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.question.service.StatisticalServiceI;

public class TStatisticalImpl {
	

	/**
	 * 针对部门、问卷约束进行测试
	 */
	@Test
	public void test1() {
		// 测试统计逻辑
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "classpath:springmvc.xml",
						"classpath:applicationContext.xml" });
		StatisticalServiceI statistical = (StatisticalServiceI) ac.getBean("statistical");
//		statistical.statDept(1, 1);
//		statistical.statDept(2, 2);
		statistical.statDept(2, 1);
//		statistical.statDept(2, 2);
		
	}

	@Test
	public void test2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {
				"classpath:springmvc.xml", "classpath:applicationContext.xml" });
		StatisticalServiceI statistical = (StatisticalServiceI) ac.getBean("statistical");
		// 测试统计逻辑
		statistical.statAll(2);
	}
	/**
	 * 针对部门、问卷进行测试
	 */
	@Test
	public void test3() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {
				"classpath:springmvc.xml", "classpath:applicationContext.xml" });
		StatisticalServiceI statistical = (StatisticalServiceI) ac.getBean("statistical");
		//gson可以对实体类、ArrayList、Map等数据结构进行格式转化
		Gson gson =new Gson();
		// 测试统计返回数据
		Map<Object,Object> allTopic = statistical.getByDeptQuestion(1, 1);
		//转换为JSON
		String str = gson.toJson(allTopic);
		System.out.println(str);
	}
	/**
	 * 针对问卷进行测试
	 */
	@Test
	public void test4() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {
				"classpath:springmvc.xml", "classpath:applicationContext.xml" });
		StatisticalServiceI statistical = (StatisticalServiceI) ac.getBean("statistical");
		//gson可以对实体类、ArrayList、Map等数据结构进行格式转化
		Gson gson =new Gson();
		// 测试统计返回数据
		Map<Object,Object> allTopic = statistical.getAllStat(1);
		//转换为JSON
		String str = gson.toJson(allTopic);
		System.out.println(str);
	}
}
