<%@ page language="java" import="java.util.*,yanshuo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户管理</title>
    
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
   <jsp:useBean id="user" class="yanshuo.UserBean" scope="session"/>
   <% if(user.getUsername()==null) response.sendRedirect("login.html");%>
   Hi,<%=user.getRealname() %><br>
   <jsp:useBean id="userManager" class="yanshuo.UserManagerBean" scope="application"/>
   <table border="1" >
    <caption>用户列表</caption>
    <tr><th>用户名</th><th>密码</th><th>姓名</th><th>性别</th><th>身份</th></tr>
    <% Iterator<UserDataBean> iter = userManager.getUserList().iterator();
       while(iter.hasNext()){
       UserDataBean userData = iter.next();%>
       <tr><td><%=userData.getUsername()%></td><td><%=userData.getPassword()%></td><td><%=userData.getRealname()%></td>
       <td><%=userData.getSex()%></td><td><%=userData.getRole()%></td></tr>
     <% } %>
   </table>
  </body>
</html>
