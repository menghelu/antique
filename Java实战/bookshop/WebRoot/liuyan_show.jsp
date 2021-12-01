<%@ page language="java" import="java.util.*,java.sql.*,chj.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'liuyan_show.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
 <body >
   <table border="1">
   <caption>我的评价</caption>
   <tr>
   <th>评价姓名</th><th>评价时间</th><th>所买书籍</th><th>评价内容</th>
   </tr>
    <%
           PreparedStatement pstmt=null;
           SqlSrvDBConn sqlsrvdb=new SqlSrvDBConn();
           Connection ct1=sqlsrvdb.getConn();
           UserTable user = (UserTable)session.getAttribute("user");
           String username = user.getUsername();
               try{
              String sql="select * from lyTable where username ="+"'"+username+"'";
                    pstmt=ct1.prepareStatement(sql);
                    ResultSet rs=pstmt.executeQuery();
                     while(rs.next()){
                     String date = rs.getString(2);
                     String title=rs.getString(3);
                     String content=rs.getString(4);   %>
             <tr>
               <td><%=username%></td>
               <td><%=date%></td>
               <td><%=title%></td>
               <td><%=content%></td>
            </tr>
                   <%   }
                   }catch(SQLException e){
                          e.printStackTrace();
                   }
             %>
        
       </table>
   <form action="main.jsp" method="post">
   <input type="submit" value="点此继续购买">
   </form>
  </body>
</html>
