<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'QuestionIndex.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    欢迎登陆 <br><a href="createQuestion/${userid}">创建新的问卷</a>
    历史问卷：<br/>
 <c:if test="${empty  requestScope.qlistyes && empty requestScope.qlistno}">
 当前没有以创建的问卷<br/>
 </c:if>
 <c:if test="${!empty  requestScope.qlistyes || !empty requestScope.qlistno }">
 未完成投票<br/>
 <table>
	 <tr>
	 	<th>序号</th>
	 	<th>题目</th>
	 	<th>进度</th>
	 	<th>操作</th>
	 	<th>状态</th>
	 </tr>
	 <c:forEach items="${requestScope.qlistno}" var="qlist" varStatus="status">
	 <tr>
	 	<td>${status.index+1}</td>
	 	<td>${qlist.title}</td>
	 	<td>50%</td>
	 	<td>
		 	<c:if test="${qlist.status ==1}"><a href="editQuestion/${qlist.id}">修改</a></c:if>
		 	<c:if test="${qlist.status ==2}">开始</c:if>
		 	<c:if test="${qlist.status ==3}">停止</c:if>
	 	</td>
	 	<td>
		 	<c:if test="${qlist.status ==1}">未发布</c:if>
		 	<c:if test="${qlist.status ==2}">未开始</c:if>
		 	<c:if test="${qlist.status ==3}">进行中</c:if>
	 	</td>
	 </tr>
	 </c:forEach>
 </table>
 <br/>
 已完成投票
 <br/>
<table>
	 <tr>
	 	<th>序号</th>
	 	<th>题目</th>
	 	<th>操作</th>
	 </tr>
	 <c:forEach items="${requestScope.qlistyes}" var="qlist" varStatus="status">
	 <tr>
	 	<td>${status.index+1}</td>
	 	<td>${qlist.title}</td>
	 	<td>
		 	<c:if test="${qlist.status ==1}">修改</c:if>
		 	<c:if test="${qlist.status ==2}">开始</c:if>
		 	<c:if test="${qlist.status ==3}">停止</c:if>
	 </tr>
	 </c:forEach>
 </table>
 </c:if>
  </body>
</html>
