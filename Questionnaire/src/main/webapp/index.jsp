<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
  <script type="text/javascript" src="js/jquery-2.0.0.js"></script>
  <script type="text/javascript">
/*   $(document).ready(function(){ 
	    jQuery.ajax({ 
	      type: 'POST', 
	      contentType: 'application/json', 
	      url: 'test/stat?deptid=1&questionid=1', 
	      dataType: 'json', 
	      success: function(data){ 
	      	alert(data.topic1.a);
	      }, 
	      error: function(){ 
	        alert("error");
	      } 
	    }); 
});   */
$(document).ready(function(){
	var postData ={deptid:1,questionid:1}
	$.post("test/stat",postData,function(data){
		alert("haha");
		alert(data);
		alert(data.topic2.a);
		//记得写JSON格式，否则认为返回的数据不能识别为json。
	},"json");
})
  </script>
</head>
<body>
<a href="test/stat?deptid=1&questionid=1">Test</a>
<br/><br/><br/>
<a href="login">登录</a>
</body>
</html>