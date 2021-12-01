<%@ page language="java" import="java.util.*,gu.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    
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
    <form action="update_jsp.jsp" method="post">
    &nbsp;&nbsp; 账&nbsp; 号&nbsp;&nbsp; <input name="username" type="text"/><br>
   &nbsp; 原 密 码<input name="password" type="password"/><br>
    &nbsp; 新 密   码<input name="password1" type="password"/><br>
     确认密码<input name="password2" type="password"/><br>
    &nbsp;&nbsp;&nbsp;
    <input type="submit" value="修改"/>
    &nbsp;&nbsp;&nbsp;
    <input type="reset"  value="重置" "/>
    </form>
  </body>
</html>
