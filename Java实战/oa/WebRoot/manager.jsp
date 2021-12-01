<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manager.jsp' starting page</title>
    
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
  <% String username = (String)session.getAttribute("username");%>
    <h1>个人信息管理界面</h1>
<h2>个人信息修改，请点击<a href="update.jsp">here</a></h2>
<form action="servlet/dakaServlet" method="post">
<input type="hidden" name="username" value="<%=username %>"/>
<input type="hidden" name="select" value="1"/>
<input type="submit" value="点此打卡上班"/>
</form>
<form action="servlet/dakaServlet" method="post">
<input type="hidden" name="username" value="<%=username %>"/>
<input type="hidden" name="select" value="2"/>
<input type="submit" value="点此打卡下班"/>
</form>
  </body>
</html>
