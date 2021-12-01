<%@ page language="java" import="java.util.*,pan.*,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'insert.jsp' starting page</title>
    
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
     String class_name=request.getParameter("class_name");
     String teacher_name=request.getParameter("teacher_name");
     String grade=request.getParameter("grade");
     String rankn=request.getParameter("rankn");
     String password=request.getParameter("password");
		String sql;
			 sql = "insert into course(username,class_name,teacher_name,grade,rankn,password) values(?,?,?,?,?,?)";
		Connection conn = database.get_connection();
		ResultSet rs = null;
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1,username);
				ps.setString(2,class_name);
				ps.setString(3,teacher_name);
				ps.setString(4,grade);
				ps.setString(5,rankn);
				ps.setString(6,password);
				rs = ps.executeQuery();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				database.closeConn(conn, null, rs);
			}
		%>
  </body>
</html>
