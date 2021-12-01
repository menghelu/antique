<%@ page language="java" import="java.util.*,yanshuo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'borrow.jsp' starting page</title>
    
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
  <h2>借书申请</h2>
  <%user users = (user)session.getAttribute("login");
     String id = users.getId();  %>
  <h3>你好，<%=id %>（学号）</h3>
    <form action="borrow_database.jsp" method="post">
         <table border="0">
		<tr>
			<td>姓名：</td>
			<td>
				<input type="text" name="username" />
			</td>
		</tr>
			<tr>
			<td>书籍编号：</td>
			<td>
				<input type="text" name="bookid" />
			</td>
		</tr>
		<tr>
			<td>书籍名称：</td>
			<td>
				<input type="text" name="bookname" />
			</td>
		</tr>
		<tr>
			<td>
				<input type="reset" value="重置"/>
			</td>
			<td>
				<input type="submit" value="提交"/>
			</td>
		</tr>
		</table>
    </form>
  </body>
</html>
