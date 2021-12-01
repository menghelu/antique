<%@ page language="java" import="java.util.*,yanshuo.*" pageEncoding="UTF-8"%>
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
  <% if(session.getAttribute("username")==null)response.sendRedirect("LoginSession/login.html");
    UserManager userManager=(UserManager)application.getAttribute("userManager");
      if(userManager==null){
          userManager=new UserManager();
          application.setAttribute("userManager", userManager);
          }
   %>
     Hi,<%=session.getAttribute("username") %>&nbsp;&nbsp;<a href="/LoginSession/servlet/LoginServlet?op=exit">退出登录</a>
     
      <table border="1">
      <caption>用户列表</caption>
      <tr><th>用户名</th><th>密码</th></tr>
      <% Iterator<User> iter=userManager.getUserList().iterator();
         while(iter.hasNext()){
         User user=iter.next();%>
         <tr><td><%=user.username %></td><td><%=user.password %></td></tr>
      <% } %> 
      </table>  
  </body>
</html>
