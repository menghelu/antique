<%@ page language="java" import="java.util.*,meng.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'gaiqian.jsp' starting page</title>
    
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
    <% String username = (String)session.getAttribute("username"); %>
    <form action="servlet/gaiqian_servlet" method="post">
      <input type="hidden" name="username" value="<%=username%>"/><br>
      初始车票编号<input type="text" name="id1"/><br>
      新改车票编号<input type="text" name="id2"/><br>
      <input type="submit" value="确定"/>
      </form>
  </body>
</html>
