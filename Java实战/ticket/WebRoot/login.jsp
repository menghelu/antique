<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <script type="text/javascript">
     function register(){
    window.location.href="register.jsp"; 
  }
  </script>
  </head>
  
  <body>
   <form action="servlet/loginServlet" method="post">
    用户名<input name="username" type="text"/><br>
    &nbsp;密&nbsp;&nbsp;码&nbsp;<input name="password" type="password"/><br>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="submit" value="登录"/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input  type="button"  value="注册" onclick="register()"/>
    
    </form>
  </body>
</html>
