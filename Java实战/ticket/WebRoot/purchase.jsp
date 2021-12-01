<%@ page language="java" import="java.util.*,meng.*,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'purchase.jsp' starting page</title>
    
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
        					 <caption>车票信息</caption>
           		<tr><th>车票编号</th><th>出发地 </th><th>目的地</th><th>价格</th><th>购买</th></tr>
    <% 
    request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
			String sql="select * from tickets";
			String username =(String) session.getAttribute("username");
				Connection conn=database.get_connection();
				ResultSet rs=null;
				try{
					PreparedStatement ps=conn.prepareStatement(sql);
					rs=ps.executeQuery();
					while(rs.next()){
					%>
					<tr><td><%=rs.getInt(1) %></td><td><%=rs.getString(2) %></td>
 					<td><%=rs.getString(3)%></td><td><%=rs.getString(4)%></td>
 					<td>
 					 <form action="servlet/add_recordServlet" method="post">
 					   <input type="hidden" name="username" value="<%=username %>"/>
 					   <input type="hidden" name="ticket_id" value="<%=rs.getInt(1) %>"/>
 					   <input type="hidden" name="departure" value="<%=rs.getString(2) %>"/>
 					   <input type="hidden" name="destination" value="<%=rs.getString(3) %>"/>
 					   <input type="hidden" name="price" value="<%=rs.getString(4) %>"/>
 					   <input type="submit" value="购买"/>
 					 </form>
 					</td></tr> 
							<% }
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					database.closeConn(conn,null,rs);
				}
		%>
  </body>
</html>
