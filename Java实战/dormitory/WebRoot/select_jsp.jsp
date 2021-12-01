<%@ page language="java" import="java.util.*,gu.*,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'select_jsp.jsp' starting page</title>
    
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
     String num=request.getParameter("num");
     List<housebean> list;
		list = new LinkedList<housebean>();
		String sql;
			 sql = "select * from house where house_id=?";
		Connection conn = db.get_connection();
		ResultSet rs = null;
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1,num);
				rs = ps.executeQuery();
				while(rs.next()){
					list.add(new housebean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				db.closeConn(conn, null, rs);
			}
			Iterator<housebean> iter = list.iterator();
		%>
		<table border="1">
         <caption>宿舍信息</caption>
           <tr><th>学号</th><th>宿舍号</th><th>姓名</th><th>学院</th><th>班级</th></tr>
		<% 
        while(iter.hasNext()){
        	housebean bd = iter.next();
         %>
<tr><td><%=bd.getStu_id() %></td><td><%=bd.getHouse_id() %></td>
 <td><%=bd.getUsername() %></td><td><%=bd.getCollege() %></td>
  <td><%=bd.getClassn() %></td></tr> 
<% } %>
	
  </body>
</html>
