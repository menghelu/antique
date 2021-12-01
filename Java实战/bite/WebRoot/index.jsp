<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
  <h1>进制转换器</h1>
    <form action="transAction.action" method="post">
     <input type="text" name="input"/><br>
     <input type="radio" name="index" value="2" checked="true">二进制</input>
     <input type="radio" name="index" value="8" >八进制</input>
     <input type="radio" name="index" value="16" >十六进制</input><br>
     <input type="reset" value="重置"/>
     <input type="submit" value ="提交"/>
    </form>
  </body>
</html>
