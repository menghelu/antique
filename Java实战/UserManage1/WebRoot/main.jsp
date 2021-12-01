<%@ page language="java" import="java.util.*,wjy.*" pageEncoding="UTF-8"%>
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
    <jsp:useBean id="user" class="wjy.UserBean" scope="session"/>
    <% if(user.getUsername()==null)  response.sendRedirect("login.html");%>
    Hi,<%=user.getRealname() %>
    <jsp:useBean id="userManager" class="wjy.UserManagerBean" scope="application"/>
    <% List<UserDataBean> userList = (List<UserDataBean>)request.getAttribute("queryResult");
    	if(userList==null) userList = userManager.getUserList();
    	QueryCondition cond=(QueryCondition) request.getAttribute("queryCond");
    	if(cond==null) cond=new QueryCondition(); %>
    <form action="servlet/QueryServlet" method="post">
    查询条件：<select name="queryField">
    <option value="1" <%=cond.getQueryField()==1?"selected":"" %>></option>
    <option value="2" <%=cond.getQueryField()==2?"selected":"" %>>用户名</option>
    <option value="3" <%=cond.getQueryField()==3?"selected":"" %>>密码</option>
    <option value="4" <%=cond.getQueryField()==4?"selected":"" %>>姓名</option>
    <option value="5" <%=cond.getQueryField()==5?"selected":"" %>>性别</option>
    <option value="6" <%=cond.getQueryField()==6?"selected":"" %>>身份</option>
    </select>
包含<input type="text" name="queryValue" value="<%=cond.getQueryValue()%>"/>
<input type="submit" value="查询"/>
    </form>
    
    <table border="1">
    <caption>用户列表</caption>
    <tr><th>用户名</th><th>密码</th><th>姓名</th><th>性别</th><th>身份</th><th>删除</th><th>修改</th></tr>
    <%Iterator<UserDataBean> iter=userList.iterator();
		while(iter.hasNext())
		{
			UserDataBean userData=iter.next(); %>
			<tr><td><%=userData.getUsername() %></td><td><%=userData.getPassword() %></td>
			<td><%=userData.getRealname() %></td><td><%=userData.getSex() %></td>
			<td><%=userData.getRole() %></td>
			<td><a href="servlet/DeleteServlet?id=<%=userData.getId()%>">删除</a></td>
			<td><a href="servlet/EditServlet?id=<%=userData.getId()%>">修改</a></td></tr>
			
			<%} %>
    </table>
  </body>
</html>
