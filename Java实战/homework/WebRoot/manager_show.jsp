<%@ page language="java" import="java.util.*,yanshuo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manager_show.jsp' starting page</title>
    
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
    
		<jsp:useBean id="ma" class="yanshuo.ManagerDataBean" scope="session"/>
		<jsp:useBean id="con" class="yanshuo.connection" scope="application"/>
		<%if(session.getAttribute("username").equals(ma.getUsername())&&session.getAttribute("password").equals(ma.getPassword())){
		List<BookDataBean> booklist = new LinkedList<BookDataBean>();
		booklist = con.manager_book();
		Iterator<BookDataBean> iter = booklist.iterator();
		%>
		<table border="1">             
         <caption>书籍列表</caption>
           <tr><th>书籍编号</th><th>书籍名称</th><th>作者姓名</th><th>书籍分类</th><th>插入</th><th>删除</th><th>删除plus</th></tr>
		<% 
        while(iter.hasNext()){
        	BookDataBean bd = iter.next();
        	//System.out.println(bd.getId());
         %>
<tr><td><%=bd.getId() %></td><td><%=bd.getBook_name() %></td>
 <td><%=bd.getAuthor_name() %></td><td><%=bd.getBook_type() %></td>
 <td><a href="add.html">插入</a></td>
 <td><a href="servlet/deleteBook_Servlet?id=<%=bd.getId() %>">删除</a></td>
 <td><form action="servlet/deleteBook_Servlet" method ="post" >
     <input type="hidden" name="id" value="<%=bd.getId() %>"/>
     <input type="submit" value="删除"/>
 </form>
 </td>
 </tr> 
<% } 
		}else{
		  response.sendRedirect("manager.jsp");
		}
     %>
  </body>
</html>
