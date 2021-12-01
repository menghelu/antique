<%@ page language="java" import="java.util.*,meng.*,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
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
    <table border="1">
        					 <caption>购买信息</caption>
           		<tr><th>用户名</th><th>车票编号</th><th>出发地 </th><th>目的地</th><th>价格</th><th>改签</th>
           		<th>退票</th></tr>
    <% 
    request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
			String sql="select * from record where username=?";
			String username =(String) session.getAttribute("username");
				Connection conn=database.get_connection();
				ResultSet rs=null;
				try{
					PreparedStatement ps=conn.prepareStatement(sql);
					ps.setString(1,username);
					rs=ps.executeQuery();
					while(rs.next()){
					%>
					<tr><td><%=rs.getString(1) %></td><td><%=rs.getInt(2) %></td><td><%=rs.getString(3) %></td>
 					<td><%=rs.getString(4)%></td><td><%=rs.getString(5)%></td>
 					<td><a href="gaiqian.jsp">改签</a></td><td><a href="servlet/deleteTicket_Servlet?id=<%=rs.getInt(2) %>">删除</a></td>
 					</tr> 
							<% }
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					database.closeConn(conn,null,rs);
				}
		%>
  </body>
</html>
