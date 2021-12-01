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
    <h3>注册新用户</h3>
    <form action="servlet/RegisterServlet" method="post">
    用户名：<input type="text" name="username"/><br>
    密码：<input type="password" name="password"/><br>
    密码验证：<input type="password" name="confirm"/><br>
    姓名：<input type="text" name="realname"/><br>
    性别：男<input type="radio" name="sex" value="男" checked/> &nbsp;&nbsp; 女<input type="radio" name="sex" value="女"/><br>
    身份：<select name="role"> <option value="用户"> 用户 </option>
    <option value="管理员">管理员</option>
    </select><br>
    <input type="submit" value="提交">
    </form>
  </body>
</html>
