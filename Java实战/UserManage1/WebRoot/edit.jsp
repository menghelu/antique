<%@ page language="java" import="java.util.*,wjy.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息</title>
    
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
  <% UserDataBean userData = (UserDataBean)request.getAttribute("object_user"); %>
     <h3>修改</h3>
    <form action="servlet/EditServlet" method="post">
    用户名：<input type="text" name="username" value="<%=userData.getUsername()%>" readonly/><br>
    密码：<input type="password" name="password" value="<%=userData.getPassword()%>" /><br>
    密码验证：<input type="password" name="confirm" value="<%=userData.getPassword()%>" /><br>
    姓名：<input type="text" name="realname" value="<%=userData.getRealname()%>" /><br>
    性别： <% if(userData.getSex().equals("男")){%>
    男<input type="radio" name="sex" value="男" checked/> &nbsp;&nbsp; 女<input type="radio" name="sex" value="女"/><br>
 <%}else{ %>
    男<input type="radio" name="sex" value="男" /> &nbsp;&nbsp; 女<input type="radio" name="sex" value="女" checked/><br>
    <%} %>
    身份：<select name="role"> <option value="用户" <%=userData.getRole().equals("用户")?"selected":"" %> > 用户 </option>
    <option value="管理员"  <%=userData.getRole().equals("管理员")?"selected":"" %> >管理员</option>
    </select><br>
    <input type="submit" value="提交">
    </form>
  </body>
</html>
