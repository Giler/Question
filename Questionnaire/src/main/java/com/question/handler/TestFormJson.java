package com.question.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.question.entity.Statistical;
import com.question.entity.Topic;
import com.question.service.StatisticalServiceI;
@Controller
@RequestMapping("test")
public class TestFormJson {

	    private static Gson gson=new Gson(); 
	    @Resource
	    private StatisticalServiceI statisticaService;
	    /** 
	     * @MethodName : toJson 
	     * @Description : 将对象转为JSON串，此方法能够满足大部分需求 
	     * @param src 
	     *            :将要被转化的对象 
	     * @return :转化后的JSON串 
	     */ 
	   
	    @ResponseBody
	    @RequestMapping(value="/stat")
	    public  Object toJson(@RequestParam("deptid")Integer deptId,@RequestParam("questionid")Integer questionnaireId) { 
	    	System.out.println("deptId: "+deptId+"questionnaireId: "+questionnaireId);
	        if (deptId==null || questionnaireId== null) {  
	            return gson.toJson(JsonNull.INSTANCE);  
	        } 
	        Map<Object, Object> questions = statisticaService.getByDeptQuestion(deptId, questionnaireId);
	        return gson.toJson(questions);

	    }
	    @ResponseBody
	    @RequestMapping(value="/statArr")
	    public  Object toJsonArr(@RequestParam(value="deptid[]")Integer[] deptId,@RequestParam("questionid")Integer questionnaireId) { 
	    	ArrayList<Map<Object, Object>> list = new ArrayList<Map<Object,Object>>();
	    	Integer deptidi;
	    	for(int i=0;i<deptId.length;i++){
	    		deptidi = deptId[i];
	    		System.out.println("deptId: "+deptId[i]+"questionnaireId: "+questionnaireId);
		        if (deptId[i]==null || questionnaireId== null) {  
		            return gson.toJson(JsonNull.INSTANCE);  
		        } 
		        Map<Object, Object> questions = statisticaService.getByDeptQuestion(deptidi, questionnaireId);
		        list.add(questions);
	    	}
	    	System.out.println(gson.toJson(list));
	        return gson.toJson(list);

	    }
	    @ResponseBody
	    @RequestMapping(value="/statDeptArr")
	    public  Object toDeptArr(@RequestParam("questionid")Integer questionnaireId) { 
	    	Map<Object, Object> selePer = new HashMap<Object, Object>();
	    	Map<Object, Object> deptSele = null;
			Map<Object, Object> topicDept =  new HashMap<Object, Object>();
			
	    	//搜出提的个数；
	    	List<Topic> topicItem = statisticaService.getTopicSumByDeptID(questionnaireId);
	    	Integer topicNum = topicItem.size();
	    	
	    	//根据题进行遍历
	    	for(int i=0;i<topicNum;i++){
	    		List<Statistical> deptItem = statisticaService.getDeptSumByQuesTopic(questionnaireId, topicItem.get(i).getId());
	    		Integer deptNum = deptItem.size();
	    		//只能在这里新建，逻辑问题，在外则数据重复，在内则数据不全。topic相同
	    		deptSele = new HashMap<Object, Object>();
	    		for(int j=0;j<deptNum;j++){
	    			selePer = statisticaService.getDept(questionnaireId, topicItem.get(i).getId(), deptItem.get(j).getDeptId());
	    			deptSele.put("deptId"+ deptItem.get(j).getDeptId(), selePer);
	    		}
	    		topicDept.put("topicId"+topicItem.get(i).getId(), deptSele);	
	    		
	    	}
	        return gson.toJson(topicDept);

	    }
}
