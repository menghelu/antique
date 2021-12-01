<%@ page language="java" import="java.util.*,yanshuo.*,java.text.*,java.sql.*,java.io.*,java.util.Date" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'return_database.jsp' starting page</title>
    
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
		String userid = request.getParameter("userid");
        String bookid1 = request.getParameter("bookid");
        int bookid = Integer.parseInt(bookid1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String return_date = df.format(new Date());// new Date()为获取当前系统时间
        String sql="update record set return_date = ? where users_id= ? and book_id =?";
			Connection conn=database.get_connection();
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,return_date);
				ps.setString(2,userid);
				ps.setInt(3,bookid);
				rs=ps.executeQuery();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				database.closeConn(conn,null,rs);
			}
			response.sendRedirect("return_success.jsp");
     %>
  </body>
</html>
