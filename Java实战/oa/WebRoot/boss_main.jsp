<%@ page language="java" import="java.util.*,wang.*,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'boss_main.jsp' starting page</title>
    
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String select = request.getParameter("select");
		if(username.equals("boss")&&password.equals("boss")){
			if(select.equals("2")){
				String sql="delete from daka";
			    Connection conn=database.get_connection();
				ResultSet rs=null;
				try{
					PreparedStatement ps=conn.prepareStatement(sql);
					rs=ps.executeQuery();
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					database.closeConn(conn,null,rs);
				}
			}
			String sql="select * from daka";
				List<dakaBean> list = new LinkedList<dakaBean>();
				Connection conn=database.get_connection();
				ResultSet rs=null;
				userBean users=new userBean();
				try{
					PreparedStatement ps=conn.prepareStatement(sql);
					rs=ps.executeQuery();
					while(rs.next()){
					list.add(new dakaBean(rs.getString(1),rs.getString(2),rs.getString(3)));
				}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					database.closeConn(conn,null,rs);
				}
				Iterator<dakaBean> iter = list.iterator();
				%>
						<table border="1">
        					 <caption>员工打卡情况</caption>
           						<tr><th>员工编号</th><th>上班打卡时间 </th><th>下班打卡时间</th></tr>
								<% 
        						while(iter.hasNext()){
        							dakaBean bd = iter.next();
         									%>
										<tr><td><%=bd.getUsername() %></td><td><%=bd.getBegin_time() %></td>
 										<td><%=bd.getEnd_time() %></td></tr> 
							<% }
		}else{
			response.sendRedirect("boss_failure.html");
		}
		%>
  </body>
</html>
