<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set value="${pageContext.servletContext.contextPath }" var="ctx"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'NewQuestion.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript">
  	//window.location.href="testReload"; 
  	/* window.onbeforeunload = function() {
            var n = window.event.screenX - window.screenLeft;
            var b = n > document.documentElement.scrollWidth - 20;
            if (!(b && window.event.clientY < 0 || window.event.altKey)) {
                alert("开始刷新页面了....");
                window.event.returnValue = "真的要刷新页面么？";     //这里可以放置你想做的操作代码
            }
        };   */
  	$(function(){
  		
  		
  		
  		/*抽象方法，简化代码*/
  		function createQues(data,type){
  			var str="."+data.title+"<input type='hidden' name='id' value='"+data.id+"'/>";
  			//这里把新建的问题的id得到是为了编辑与删除用
  			var create="<br/><input type='"+type+"' disabled='true'>";
  			//js ascii转字符
  		 	for(var i=0;i<data.topicLen;i++){
  				//str=str+create;
  				var a=String.fromCharCode(97+i);
  				//alert(a);
  				str=str+create+data[a];
  			}
  			//str=str+create;
  			//下面这个方法是以前写的不好，可扩展性太低
  		/* 	if(data.a==null){
  				str=str+create;
  			}else{
  				str=str+create+data.a;
  				if(data.b!=null){
  					str=str+create+data.b;
  					if(data.c!=null){
  						str=str+create+data.c;
  						if(data.d!=null){
  							str=str+create+data.d;
  							if(data.e!=null){
  								str=str+create+data.e;
  								if(data.f!=null){
  									str=str+create+data.f;
  								}
  							}
  						}
  					}
  					
  	  			}
  			} */
  			
  			return str;
  		}
			
  	var xuhao=1;
  	var qid=$("#qid").val();
  	//这里新建一个节点来复制即将要被覆盖的编辑节点
  	var $preDiv=null;
  	var $quxiao=null;
  	//这里的ifUseQx是用来判断下面的编辑按钮返回值用哪个
  	var ifUseQx=0;
  	//获取当前正在编辑的序号值
  	var xuhaoOnEdit="0";
  	//这里的这个变量是用来在点击取消时判断是取消编辑还是返回到上一步操作：0：取消操作，1：返回上一步操作
  	var ifReturn=0;
  	var args={};
  	//当页面加载完毕时获取到这个问卷所对应的所有的问题
	var attr={};
	var qid=$("#qid").val();
	//alert(qid);
	attr["questionnaire.id"]=qid;
	//alert(attr[]);
	$.post("getAllTopicByQid",attr,function(data){
		//alert(data);
			var obj = JSON.parse(data);
			for(var i=0;i<obj.length;i++){
				var h=obj[i];
				abstractMethod(h);
				//$("#asd").append($(createPreQues(h)).clone(),null);
			}
	});
	
	//创建从后台得到的所有的问题
	function createAllQues(data){
		var btn="<br/><button class='editThis'>编辑</button><button class='removeThis'>删除</button>";
		
	}
	
	//模拟创建生成问题前的编辑项，用来编辑的时候克隆用
	function createPreQues(data){
		//获取问题ABC的数量
		var ques="";
		for(var i=0;i<data.topicLen;i++){
				//str=str+create;
				var a=String.fromCharCode(97+i);
				ques=ques+"选项名称：<input name='"+a+"' value='"+data[a]+"'/><br/>";
			}
		
		
		var str="<div>问题名称：<input name='title' value='"+data.title+"'/><br/>"+ques+
		  	"<input type='hidden' name='type' value='"+data.type+"'/>"+
		  	"<input type='hidden' name='questionnaire.id' value='${ques.id}'/>"+
		  	"<button class='add'>新建选项</button><br/>"+ 
		  	"<button class='savewenti'>保存</button>"+
		  	"<button class='deletewenti'>取消</button><br/><br/></div>";
		  	return str;
	}
	
	function abstractMethod(data,thisNode){
				var str=null;
				var writeXuhao=0;
				var btn="<br/><button class='editThis'>编辑</button><button class='removeThis'>删除</button>";
				if(ifReturn==1){
					//alert(111);
					writeXuhao=parseInt(xuhaoOnEdit);
				}else{
					writeXuhao=xuhao;
				}
				if(data.type==1){
					//单选
					str="<div><br/><a class='xuhao'>"+writeXuhao+"</a>"+createQues(data,"radio")+btn+"</div>";
					
			}else if(data.type==2){
				//多选
				str="<div><br/><a class='xuhao'>"+writeXuhao+"</a>"+createQues(data,"checkbox")+btn+"</div>";
					
			}else if(data.type==3){
				//问答
				str="<div><br/><a class='xuhao'>"+writeXuhao+"</a>"+createQues(data,"text")+btn+"</div>";
					
			}else{
				//下个版本扩展
			}
				
				var $editAndRemove=null;
				if(ifReturn==0){
					//alert(111);
					xuhao+=1;
					$editAndRemove=$(str).appendTo("#end");
				}else{
					ifReturn=0;
					$editAndRemove=$(str).replaceAll(thisNode);//这里有逻辑问题
				}
				//为编辑和删除两个按钮添加单击事件
				
				//删除
				$editAndRemove.find(".removeThis").click(function(){
					if($("#continiu").val()=="0"){
						
						$("#continiu").val("1");
					//得到要删除的问题的id
					var id=$(this).prevAll("input[name='id']").attr("value");
					//alert(id);
					var quid={"id":id};
					//通过异步传输的方式在数据库中删除
					$.post("deleteQues",quid,function(data){
						//alert(data);
						$("#continiu").val("0");
	  					});
					$(this).parent().remove();
					//删除当前问题之后，对问题的序号重新排序
					//对序号进行遍历
					var i=1;
					$(".xuhao").each(function(){
						$(this).text(i++);
					});
					//把序号的数值减一
					xuhao--;
					}else{
						alert("请完成当前编辑！！！");
					}
					
				});
				//编辑
				$editAndRemove.find(".editThis").click(function(){
					var id=$(this).prevAll("input[name='id']").attr("value");
					$("#ajax").val("updatequs");
					args["id"]=id;
					//$(this).parent().remove();
					//$thisNode.show();
					//alert($thisNode);
					if($("#continiu").val()=="0"){
  					$preDiv=$(this).parent().clone(true);
  					 var $thisNode2=$(createPreQues(data)).clone();
  					$("#continiu").val("1");
  					ifReturn=1;
  					//用户点击编辑则重写点击取消按钮的方法：返回上一步操作
  					$thisNode2.find(".deletewenti").click(function(){
  						return removeQsTion(this);
  					});
  					$thisNode2.find(".savewenti").click(function(){
  						//当点击编辑后再，则是更新操作保存
  						//savequs="updatequs";
  						//$("#ajax").val("updatequs");
		  				return saveQsTion(this);
		  			}); 
  					$thisNode2.find(".add").click(function(){
		  				return addOptions(this);
		  			});
  					$thisNode2.find(".deleteOptions").click(function(){
  						//顺序不能变
  						$(this).prevAll("a:first").remove();
  		  				$(this).prevAll("input:first").remove();
  		  				$(this).nextAll("br:first").remove();
  		  				$(this).remove();
  					});
  					$(this).parent().replaceWith($thisNode2);
  					
  					xuhaoOnEdit=$preDiv.find(".xuhao").text();
					}else{
						alert("请完成当前编辑！！！");
					}
				});
	}
	
	
  	//用户点击保存问题后的函数
  		function saveQsTion(dNode){
  			var savequs=$("#ajax").val();
  			//var $thisNode2=null;
  			var $thisNode=$(dNode).parent();
  			
  			//克隆当前节点，等下面编辑时候使用
  			//$thisNode2=$thisNode.clone();
  			var $input=$(dNode).siblings("input");
  			//alert(qid);
  			
  			//创建Json对象
			function createJson(prop, val){
				// 如果 val 被忽略
				if(typeof val === "undefined") {
					// 删除属性
					delete args[prop];
				}else{
					// 添加 或 修改
					args[prop] = val;
				}
			}
			//循环遍历得到的表单选项
  			$input.each(function(index){
  				//alert(index);
  				//alert($(this).attr("name")+":"+$(this).val());
  				//去除首位空格
  				var name=$(this).attr("name").trim();
  				var value=$(this).val().trim();
  				createJson(name,value); 
  			});
			//回调方法，问题异步保存成功后在显示保存过的问题，并且删除当前编辑项
			//alert(savequs);
  			$.post(savequs,args,function(data){
  				abstractMethod(data,$thisNode);
  				//每次保存或更新后都要把ajax的值设置为原始值
  				$("#ajax").val("savequs");
  				//保存成功则删除当前编辑项
  				$thisNode.remove();
  			});
  			//alert(111);
  			//这里的args为全局变量，为了不影响下一个问题添加时出现上一个累加情况所以需要把这个变量置空
  			args={};
  			$("#continiu").val("0");
  		}
  		
  		//用户点击取消问题后的函数
  		function removeQsTion(dNode){
  			//获取当前对象的父节点（要删除的div节点）
  			//alert(ifReturn);
  			var $thisNode=$(dNode).parent();
  			if(ifReturn==0){
	  				$thisNode.remove();
	  				$("#continiu").val("0");
  		 	}else{
  		 		//$thisNode2=$thisNode.clone(true);
  				$thisNode.replaceWith($preDiv);
  				//ifUseQx=1;
  				$("#continiu").val("0");
  				$preDiv=null;
  				ifReturn=0;
  			}
  		}
  		/*在新建问题项时增加选项按钮的思路：
  		1、获取当前控件前的input数量
  		2、新建一个数组像这样：args{1,"a",2:"b"}，来控制新建按钮的name属性值（这也是最主要的问题）
  		3、如果默认的按钮数为2，那么起始的input数量为5个下一个name为c则args{6:"c",7:"d",8:"e",9:"f"}
  		4、将获取到的值传入作为name、并把新建的控件放在当前控件之前的div里
  		5、我们自己控制单选多选的默认按钮数量为2
  		*/
  		//用户点击新建选项的函数
  		function addOptions(dNode){
  			//新建json数组
  			var names={6:"c",7:"d",8:"e",9:"f"};
  			//获取当前按钮钱的input数量
  			var $input=$(dNode).siblings("input");
  			//var $div=$(dNode).siblings("div");
  			var index=$input.length;
  			//得到name值
  			var name=names[index+1];
  			//新建按钮html语句，并且放入到控件之前的div里
  			var str="<a>选项名称：</a><input type='text' name='"+name+"'/><button class='deleteOptions'>-</button><br>";
  			if(parseInt(index)<9){
  				$(dNode).before(str);
  			}else{
  				alert("选项数已达上限！！！");
  			}
  			$(dNode).parent().find(".deleteOptions").click(function(){
  				//顺序不能变
  				$(this).prevAll("a:first").remove();
  				$(this).prevAll("input:first").remove();
  				$(this).nextAll("br:first").remove();
  				$(this).remove();
  			});
  			
  		}
  		
  		
  		//先这么写，功能实现后在简化代码
  		//添加单选按钮
  		//这里的append后面不能跟两个find因为这样的话两个find的对象不同
  		//所以new一个jQuery对象来获取
  		//这里使用appendTo的原因也是对象的问题
  		//当前只能添加一个问题，这个问题添加成功后才能添加下一个问题
  		
  		//将单选多选的预留选项的添加写在下面这个方法中
  		
  		function radioAndCheckbox(type){
  			var str="<div>问题名称：<input name='title'/><br/>"+
   			  		"选项名称：<input name='a'/><br/>"+
	   			 	"选项名称：<input name='b'/><br/>"+
	  			  	"<input type='hidden' name='type' value='"+type+"'/>"+
	  			  	"<input type='hidden' name='questionnaire.id' value='${ques.id}'/>"+
	  			  	"<button class='add'>新建选项</button><br/>"+ 
	  			  	"<button class='savewenti'>保存</button>"+
	  			  	"<button class='deletewenti'>取消</button><br/><br/></div>";
	  			  	return str;
  		}
  		
  		
  		$("#radiobtn").click(function(){
  			if($("#continiu").val()=="0"){
  				$("#continiu").val("1");
  				var str=radioAndCheckbox(1);
  			var $radiobtn=$(str).appendTo("#end");
  				  			  $radiobtn.find(".deletewenti").click(function(){
  				  				  return removeQsTion(this);
  				  			  });
  				  			  $radiobtn.find(".savewenti").click(function(){
  				  				  return saveQsTion(this);
  				  			  }); 
  				  			  $radiobtn.find(".add").click(function(){
  				  				  //$(this).prepend("选项名称：<input name='b'/><br/>");
  				  				  return addOptions(this);
  				  				  //return false;
  				  			  });
  			}else{
  				alert("请完成当前编辑!!!");
  			}
				  			
  		});
  		
  		//添加多选按钮
		$("#checkboxbtn").click(function(){
			if($("#continiu").val()=="0"){
				$("#continiu").val("1");
				var str=radioAndCheckbox(2);
			var $checkboxbtn=$(str).appendTo("#end");
			 				 $checkboxbtn.find(".deletewenti").click(function(){
	  				  		 	return removeQsTion(this);
	  			  			 });
				  			 $checkboxbtn.find(".savewenti").click(function(){
				  				return saveQsTion(this);
				  			 });
				  			 $checkboxbtn.find(".add").click(function(){
 				  				  //$(this).prepend("选项名称：<input name='b'/><br/>");
 				  				  return addOptions(this);
 				  				  //return false;
 				  			  });
			}else{
	  			alert("请完成当前编辑!!!");
	  			}
  		});
		
  		//添加问答题
		$("#textbtn").click(function(){
			if($("#continiu").val()=="0"){
			    $("#continiu").val("1");
			var $textbtn=$("<div border='1'>问题名称：<input name='title'/><br/>"+
		  			 		 "<input type='hidden' name='type' value='3'/>"+
		  			 		 "<input type='hidden' name='questionnaire.id' value='${qid}'/>"+
			  			 	 "<button class='savewenti'>保存</button>"+
				  			 "<button class='deletewenti'>取消</button><br/><br/></div>").appendTo("#end");
			 				 $textbtn.find(".deletewenti").click(function(){
	  						  	return removeQsTion(this);
	  			 			 });
			  			  	 $textbtn.find(".savewenti").click(function(){
			  					return saveQsTion(this);
			  			  	 });
			}else{
	  			alert("请完成当前编辑!!!");
	  			}
		});
  		
  		
  		//当问卷的标题简介发生变化是，异步更新
  		$(".titOrBrief").change(function(){
  			var name=$(this).attr("name");
  			var val=$(this).val();
  			var qid=$("#qid").val();
  			var args={"qid":qid};
  			args[name]=val;
  			//异步
  			$.post("ajaxOther",args,function(data){
  				alert(data);
  			});
  		});
  	});
  	
  </script>
  </head>
  
  <body>
    <!-- <button id="btn">新建</button> -->
    <input type="hidden" value="savequs" id="ajax">
    <button id="radiobtn" class="new">添加单选题</button><button id="checkboxbtn" class="new">添加多选题</button>
	    <button id="textbtn" class="new">添加问答题</button><br/>
	    <input type="hidden" value="0" id="continiu">
    <%-- <form action="" method="post"> --%>
    <input type="hidden" value="${ques.muid}">
    <input type="hidden" value="${ques.id}" id="qid">
	    <input name="title" type="text" value="${ques.title}" class="titOrBrief"/><br/>
	    <input name="brief" type="text" value="${ques.brief}" class="titOrBrief"/><br/><br/>
	   
	    <div id="end">
			<%-- <c:forEach items="${requestScope.topics}" var="topic" varStatus="status">
				<div><a class="xuhao">${status.index+1}</a>.${topic.title}<br/>
				<c:if test="${topic.type==1}">
					
				</c:if>
				</div>
			</c:forEach> --%>	    
	    </div>
	    <br/>是否匿名：
	    匿名:<input type="radio" name="anonymous" value="0" class="titOrBrief"/>
	    不匿名:<input type="radio" name="anonymous" value="1" class="titOrBrief"/><br/>
   <%--  </form> --%>
   <span id="asd"></span>
  </body>
</html>
