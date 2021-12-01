<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
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
  hi,<s:property value="#session.user.realname"/><br>
  <table border="1">
<caption>用户列表</caption>
<tr><th>用户名</th><th>密码</th><th>姓名</th><th>性别</th></tr>
<s:iterator value="#application.userManager.userList"> 
<tr><td><s:property value="username"/></td><td><s:property value="password"/></td>
<td><s:property value="realname"/></td><td><s:property value="sex"/></td></tr>
</s:iterator>
</table>
  </body>
</html>
