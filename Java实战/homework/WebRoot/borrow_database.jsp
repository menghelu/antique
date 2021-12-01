<%@ page language="java" import="java.util.*,yanshuo.*,java.text.*,java.sql.*,java.io.*,java.util.Date" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'borrow_database.jsp' starting page</title>
    
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
		user users =(user) session.getAttribute("login");
		String userid = users.getId();
        String bookid = request.getParameter("bookid");
        String username = request.getParameter("username");
        String bookname = request.getParameter("bookname");
        String password = users.getPassword();      
          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String borrow_date = df.format(new Date());// new Date()为获取当前系统时间
        String return_date = "待还";
        String sql="insert into record(users_id,book_id,username,bookname,borrow_date,return_date,password) values(?,?,?,?,?,?,?)";
			Connection conn=database.get_connection();
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,userid);
				ps.setString(2,bookid);
				ps.setString(3,username);
				ps.setString(4,bookname);
				ps.setString(5,borrow_date);
				ps.setString(6,return_date);
				ps.setString(7,password);
				rs=ps.executeQuery();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				database.closeConn(conn,null,rs);
			}
			response.sendRedirect("borrow_success.jsp");
     %>
  </body>
</html>
