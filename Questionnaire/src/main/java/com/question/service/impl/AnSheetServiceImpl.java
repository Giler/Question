package com.question.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.question.dao.AnSheetDao;
import com.question.entity.AnSheet;
import com.question.service.AnSheetServiceI;
@Service("anSheetService")
public class AnSheetServiceImpl implements AnSheetServiceI {

	@Resource
	private AnSheetDao anSheetDao;
	@Override
	public ArrayList<Map<Object, Object>> getPersonStat(Integer ordusersID, Integer questionnaireId) {
		// TODO Auto-generated method stub
		ArrayList<Map<Object, Object>> personSheet= new ArrayList<Map<Object,Object>>();
		List<AnSheet> anSheetList =null;
		int count =1;
		String hql = "from AnSheet a where a.questionnaireId= " + questionnaireId + "and a.userId= " + ordusersID;
		anSheetList = anSheetDao.getMore(hql);
		for(AnSheet s : anSheetList){
			Map<Object, Object> sheetContent = new HashMap<Object, Object>();			
			sheetContent.put(count++, s.getContent());
			personSheet.add(sheetContent);			
		}		
		return personSheet;
	}

}
