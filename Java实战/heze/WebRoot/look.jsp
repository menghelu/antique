<%@ page language="java" import="java.util.*,pan.*,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'look.jsp' starting page</title>
    
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
     String username=request.getParameter("username");
     String password=request.getParameter("password");
     List<coursebean> list;
		list = new LinkedList<coursebean>();
		String sql;
			 sql = "select * from course where username=? and password=?";
		Connection conn = database.get_connection();
		ResultSet rs = null;
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1,username);
				ps.setString(2,password);
				rs = ps.executeQuery();
				while(rs.next()){
					list.add(new coursebean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				database.closeConn(conn, null, rs);
			}
			Iterator<coursebean> iter = list.iterator();
		%>
		<table border="1">
         <caption>课程信息</caption>
           <tr><th>学生姓名</th><th>课程名称</th><th>教师姓名</th><th>分数</th><th>排名</th></tr>
		<% 
        while(iter.hasNext()){
        	coursebean bd = iter.next();
         %>
<tr><td><%=bd.getUsername() %></td><td><%=bd.getClass_name() %></td>
 <td><%=bd.getTeacher_name() %></td><td><%=bd.getGrade() %></td>
  <td><%=bd.getRankn() %></td></tr> 
<% } %>
	
  </body>
</html>
