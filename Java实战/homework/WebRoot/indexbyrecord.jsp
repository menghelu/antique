<%@ page language="java" import="java.util.*,yanshuo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
</script>
    
    <title>My JSP 'indexbyrecord.jsp' starting page</title>
    
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
     <%
        request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		connection con = new connection();
		List<RecordDataBean> recordlist = new LinkedList<RecordDataBean>();
		
			recordlist = con.index_record(id,password);
		Iterator<RecordDataBean> iter = recordlist.iterator();
		%>
		<table border="1">
         <caption>借阅记录</caption>
           <tr><th>学号</th><th>书籍编号</th><th>学生姓名</th><th>书籍名称</th><th>借阅日期</th><th>归还日期</th></tr>
		<% 
        while(iter.hasNext()){
        	RecordDataBean bd = iter.next();
        	//System.out.println(bd.getBook_id());
         %>
<tr><td><%=bd.getUsers_id() %></td><td><%=bd.getBook_id() %></td>
 <td><%=bd.getUsername() %></td><td><%=bd.getBookname() %></td>
  <td><%=bd.getBorrow_date() %></td><td><%=bd.getReturn_date() %></td>
  </tr>
<% } %>
  </body>
</html>
