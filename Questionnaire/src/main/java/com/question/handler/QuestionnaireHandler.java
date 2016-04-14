package com.question.handler;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.question.entity.Questionnaire;
import com.question.entity.Topic;
import com.question.service.QuestionnaireService;
import com.question.service.TopicService;
/**
 * 此类涉及到问卷的新建、修改、保存等操作
 */
@Controller
public class QuestionnaireHandler {
	/*private Gson gson = new GsonBuilder()
			.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)  
			.create(); */
	 private static Gson gson=new Gson();
	
	@Autowired
	@Qualifier("questionnarieService")
	private QuestionnaireService questionnaireService;
	
	@Autowired
	@Qualifier("topicService")
	private TopicService topicService;
	
	//ModelAttribute
	/**
	 * 这里的ModelAttribute是为了异步更新问卷的方法，我们根据这个问卷的id把问卷的所有信息查出来
	 * @param id
	 */
	@ModelAttribute
	public void getQuestionnaire(@RequestParam(value="qid",required=false) Integer id,
			Map<String,Object> map){
		if(id!=null){
			map.put("questionnaire", questionnaireService.getOneById(id));
		}
	}
	
	
	//生成问卷编号的方法
	public String getNumber(){
		String number="";
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		number=sdf.format(date);
		number=number.replaceAll(":", "");
		number=number.replaceAll("-", "");
		number=number.replaceAll(" ", "");
		return number; 
	}
	
	//生成问题长度的方法
	public int getTopicLen(Topic topic){
		//Object o=new Object();
		int len=0;
		//得到类对象
				Class userCla = (Class) topic.getClass();
				//得到类中的所有属性集合
				Field[] fs = userCla.getDeclaredFields ();
				for(int i=0;i<fs.length;i++){
					Field f = fs[i];
			        f.setAccessible( true ); // 设置些属性是可以访问的
			        Object val;
					try {
						val = f.get(topic);
						if(val!=null){
				        	len=(len+1);
				        }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        
				}
				//判断是更新还是新建
				//System.out.println(topic.getId());
				if(topic.getId()!=null){
					len=len-4;
				}else{
					len=len-3;
				}
		return len;
	}
	/**
	 * 这个方法是用户点击创建新的问卷后的方法
	 * 实现思路：
	 * 进入这个方法后就开始创建问卷（在数据库中新建保存），并返回新创建的问卷的id，
	 * 前台新建问题的保存是通过异步的方式保存的
	 */
	//点击新建问卷跳到的方法，用户点击新建问卷，就保存到数据库
	//此问卷的标题简介其他等问题通过前台的Ajax实现动态保存
	//这个方法里新建之后再根据id在数据库中查询
	@RequestMapping(value="/createQuestion/{userid}",method=RequestMethod.GET)
	public String createQuestion(@PathVariable("userid") Integer id,Map<String,Object> map){
		//这里新建问卷对象
		Questionnaire qt=new Questionnaire();
		//给新建的问卷的标题等赋默认值
		qt.setMuid(id);
		qt.setTitle("问卷标题");
		qt.setBrief("问卷简介");
		qt.setCratetime(new Date());
		qt.setNumber(getNumber());
		qt.setStatus(1);
		int qid=questionnaireService.savaQues(qt);
		//前台使用SpringMVC的表单回显功能，这里使用map将新建的对象放入
		//map.put("questionnaire", new Questionnaire());
		map.put("userid", id);
		//将得到的问卷的id传入前台，以便异步保存
		map.put("qid", 1);
		return "redirect:/editQuestion/"+qid;
	}
	
	//根据问卷id修改问卷
	@RequestMapping(value="/editQuestion/{qid}",method=RequestMethod.GET)
	public String editQuestion(@PathVariable("qid") Integer id,Map<String,Object> map){
		//System.out.println(id);
		Questionnaire ques=questionnaireService.getOneById(id);
		//List<Topic> topics=topicService.getByQid(id);
		map.put("ques", ques);
		//map.put("topics",topics);
		return "Questionnaire";
	}
	
	//异步根据问卷id得到所有问卷问题的方法
	@ResponseBody
	@RequestMapping(value="getAllTopicByQid",produces = "text/html;charset=UTF-8")
	public Object getAllTopicByQid(@Valid Topic topic){
		System.out.println(topic.getQuestionnaire().getId());
		//解除代理
		Questionnaire q=new Questionnaire();
		q.setId(topic.getQuestionnaire().getId());
		//topics=new ArrayList<Topic>();
		//Map<Integer,Object> map=new HashMap<Integer, Object>();
		List<Topic> topics=topicService.getByQid(topic.getQuestionnaire().getId());
		/*for(int i=0;i<topics.size();i++){
			topics.get(i).setQuestionnaire(q);
			
		}*/
		//map.put("topics", topics);
//		Type type = new TypeToken<List<Topic>>(){}.getType();
//		String json=gson.toJson(topics , type);
		
//		System.out.println("toJson   "+json);
		//System.out.println(gson.toJson(topics));
		return gson.toJson(topics);
	}
	
	/**
	 * 用户在前台异步保存问题的方法
	 */
	@ResponseBody
	@RequestMapping("savequs")
	public Topic savequs(@Valid Topic topic){
		//topicDao.saveOrUpdate(topic);
		topic.setTopicLen(getTopicLen(topic));
		int id=topicService.createTopic(topic);
		//System.out.println(id);
		topic.setId(id);
		/*System.out.println("title:"+topic.getTitle());
		System.out.println("a:"+topic.getA());
		System.out.println("type:"+topic.getType());
		System.out.println("qid:"+topic.getQuestionnaire().getId());*/
		return topic;
	}
	
	/**
	 * 用户在前台异步更新问题的方法
	 */
	@ResponseBody
	@RequestMapping("updatequs")
	public Topic updatequs(@Valid Topic topic){
		//System.out.println(111);
		topic.setTopicLen(getTopicLen(topic));
		topicService.updateTopic(topic);
		return topic;
	}
	
	/**
	 * 用户在前台异步删除问题打方法
	 */
	@ResponseBody
	@RequestMapping("deleteQues")
	public String deleteQues(@RequestParam("id") Integer id){
		topicService.deleteTopic(id);
		return "删除成功！！！";
	}
	
	/**
	 * 用户在前台异步更新标题、简介或是否匿名
	 * produces = "text/html;charset=UTF-8"设置这个用来设置字符集，否则前台得到的中文是乱码
	 */
	@ResponseBody
	@RequestMapping(value="ajaxOther",produces = "text/html;charset=UTF-8")
	public String ajaxOther(@Valid Questionnaire questionnaire){
		questionnaireService.createOrUpdateQues(questionnaire);
		return "更改成功";
	}
}
