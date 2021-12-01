<%@ page language="java" import="java.util.*,yanshuo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'borrow_success.jsp' starting page</title>
    
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
  <% user users =(user)session.getAttribute("login");
   String id = users.getId();
   String password = users.getPassword();
   System.out.println(id);
   %>
    <h1>借书成功，点击<a href="welcome.html">here</a>返回首页</h1>
    <form action="indexbyrecord.jsp" method="post">
    <input type="hidden" name="id" value="<%=id %>"/>
    <input type="hidden" name="password" value="<%=password %>"/><br>
    <input type="submit" value="点击查询借阅记录"/>
    </form>
  </body>
</html>
