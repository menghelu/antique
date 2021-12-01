<%@ page language="java" import="java.util.*,yanshuo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'indexbybook.jsp' starting page</title>
    
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
  <h2>如要借书，请先<a href="login.html">登录</a><br>
还没账号？点击<a href="register.html">注册</a></h2>
    <%
        request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String keyword=request.getParameter("keyword");
		String index=request.getParameter("index");
		connection con = new connection();
		List<BookDataBean> booklist = new LinkedList<BookDataBean>();
		if(index.equals("all")){
		    booklist = con.index_book(keyword,0);
		}else if(index.equals("书籍名称")){
			booklist = con.index_book(keyword,1);
		}else if(index.equals("作者姓名")){
			booklist = con.index_book(keyword,2);
		}else{
			booklist = con.index_book(keyword,3);
		}
		Iterator<BookDataBean> iter = booklist.iterator();
		%>
		<table border="1">
         <caption>书籍列表</caption>
           <tr><th>书籍编号</th><th>书籍名称</th><th>作者姓名</th><th>书籍分类</th></tr>
		<% 
        while(iter.hasNext()){
        	BookDataBean bd = iter.next();
         %>
<tr><td><%=bd.getId() %></td><td><%=bd.getBook_name() %></td>
 <td><%=bd.getAuthor_name() %></td><td><%=bd.getBook_type() %></td>
 </tr> 
<% } %>
  </body>
</html>
