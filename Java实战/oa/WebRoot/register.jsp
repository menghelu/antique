<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
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
    <form action="servlet/registerServlet" method="post">
    &nbsp;&nbsp;用户名&nbsp;&nbsp;&nbsp;<input name="username" type="text"/><br>
&nbsp;&nbsp;密&nbsp;&nbsp;码&nbsp;&nbsp;&nbsp;&nbsp; <input name="password" type="password"/><br>
&nbsp;&nbsp;电&nbsp;&nbsp;话&nbsp;&nbsp;&nbsp;&nbsp;<input name="phone" type="text"/><br>
部门编号<input name="dept_id" type="text"/><br>
真实姓名<input name="realname" type="text"/><br>
&nbsp;&nbsp;邮&nbsp;&nbsp;箱&nbsp;&nbsp;&nbsp;&nbsp;<input name="email" type="text"/><br>
      <input  type="reset"  value="重置" />
    <input type="submit" value="确定"/>
  
    
    </form>
  </body>
</html>
