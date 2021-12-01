<%@ page language="java" import="java.util.*,yanshuo.*" pageEncoding="UTF-8"%>
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
   <form action="servlet/manager_loginServlet" method="post">
   <h2>管理员登录界面</h2>
    <label>账号</label>
    <input name="username" type="text"/><br>
    <label>密码</label>
    <input name="password" type="password"/><br>
    <tr><td height="15"></td></tr>
		<tr>
		  <td align="center">验 证 码
		  </td>
		  <td align="left">
			<input type="text" maxlength="20" size="5" name="uyzm">
			  <img border=0 src="servlet">
		  </td>
		</tr><br>
    &nbsp;&nbsp;&nbsp;&nbsp;
    
    <input type="reset" value="重置"/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input  type="submit"  value="登录" "/>
    
    </form>
  </body>
</html>
