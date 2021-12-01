<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <form action="servlet/updateServlet" method="post">
      修改人工号<input type="text" name="id"/><br>
  修改后内容<input type="text" name="content"/><br>
 修改的选项<select name="select">
    <option value="password">密码</option>
    <option value="phone">电话</option>
    <option value="dept_id">部门</option>
    <option value="truename">真名</option>
    <option value="email">邮箱</option>
 </select>
 <br>
 <input type="submit" value="确定"/>
 <input type="reset" value="重置"/>
    </form>
  </body>
</html>
