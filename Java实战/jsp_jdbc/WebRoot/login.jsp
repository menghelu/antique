<%@ page language="java"pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
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

  </head>
<body bgcolor="#e3e3e3">
<form action="validate.jsp" method="post">
<table>
<caption>用户登录</caption>
<tr><td>用户名：</td>
<td>
<input type="text" name="username" size="20"/>
</td>
</tr>
<tr>
<td>密码：
</td>
<td>
<input type="password"name="password" size="21"/>
</td>
</tr>
</table>
<input type="submit" value="登录"/>
<input type="reset"  value="重置"/>
</form>
如果没注册单击<a href="">这里</a>注册
</body>
</html>