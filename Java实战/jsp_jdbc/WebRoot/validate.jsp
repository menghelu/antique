<%@ page language="java"  pageEncoding="UTF-8" import="java.sql.*"%>
 <jsp:useBean id="SqlSrvDB"scope="page" class="org.easybooks.test.jdbc.SqlSrvDBConn"/>
<html>
<head>
    <title>My JSP 'validate.jsp' starting page</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
  </head>
  <body>
   <%
   request.setCharacterEncoding("utf-8");
   String usr=request.getParameter("username");
   String pwd=request.getParameter("password");
   boolean validated=false;
   //查询usertable表中的记录
   String sql="select*from userTable";
   ResultSet rs=SqlSrvDB.executeQuery(sql);
   while(rs.next())
   {
   if ((rs.getString("username").trim().compareTo(usr)==0)
   		&&(rs.getString("password").compareTo(pwd)==0))
   {
   validated=true;
   }
   }
   rs.close();
   SqlSrvDB.closeStmt();
   SqlSrvDB.closeConn();
   if(validated)
   {
   //验证成功跳转到main.jsp
   %>
   <jsp:forward page="main.jsp"/>
   <%
   }
   else
   {
   //验证失败跳转到error.jsp
    %>
    <jsp:forward page="error.jsp"/>
    <%
    }
     %>
  </body>
</html>
