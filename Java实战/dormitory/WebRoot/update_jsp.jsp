<%@ page language="java" import="java.util.*,gu.*,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update_jsp.jsp' starting page</title>
    
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
    <% String username = request.getParameter("username");
       String password = request.getParameter("password");
       String password1 = request.getParameter("password1");
       String password2 = request.getParameter("password2");
       boolean flag;
		String sql="select * from stu where username=?";
		Connection conn=db.get_connection();
		ResultSet rs=null;
		stubean stu=new stubean();
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			rs=ps.executeQuery();
			if(rs.next()){
				stu.setUsername(rs.getString(1));
				stu.setPassword(rs.getString(2));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			db.closeConn(conn,null,rs);
		}
		if(stu!=null&&password.equals(stu.getPassword()))
		  flag = true;
		else
		  flag = false;
		if(flag){
			if(password1.equals(password2)){
			   String sql1="update stu set password = ? where username = ?";
		Connection conn1=db.get_connection();
		ResultSet rs1=null;
		try{
			PreparedStatement ps1=conn1.prepareStatement(sql1);
			ps1.setString(1,password1);
			ps1.setString(2,username);
			rs1=ps1.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			db.closeConn(conn,null,rs);
		}
		response.sendRedirect("update_success.html");
		    }else{
		       response.sendRedirect("update_error2.html");
		    }
		}else{
			response.sendRedirect("update_error1.html");
		}
     %>
  </body>
</html>
