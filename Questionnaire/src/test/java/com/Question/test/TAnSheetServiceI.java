package com.Question.test;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.question.service.AnSheetServiceI;

public class TAnSheetServiceI {

	@Test
	public void test() {
		Gson gson =new Gson();
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:springmvc.xml", "classpath:applicationContext.xml" });
		AnSheetServiceI anSheetService = (AnSheetServiceI) ac.getBean("anSheetService");
		ArrayList<Map<Object, Object>> list = anSheetService.getPersonStat(1, 1);
		String str = gson.toJson(list);
		System.out.println(str);
	}

}
