package com.question.service;

import java.util.ArrayList;
import java.util.Map;

public interface AnSheetServiceI {
	/**
	 * 根据用户ID 和对应问卷查询问卷答题情况
	 * 
	 * @param ordusersID
	 *            用户ID
	 * @param questionnaireId
	 *            问卷ID
	 * @return
	 */
	public ArrayList<Map<Object, Object>> getPersonStat(Integer ordusersID, Integer questionnaireId);
}
