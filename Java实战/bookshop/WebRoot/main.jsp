<%@ page language="java" import="java.util.*,java.sql.*,chj.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品信息</title>
    
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
  
  
    
     <table>
         <caption>书籍信息</caption>
            <tr>
                <th>书籍名称</th><th>书籍价格</th><th>书籍类型</th><th>购买</th>
            </tr>
        <%
           PreparedStatement pstmt=null;
           SqlSrvDBConn sqlsrvdb=new SqlSrvDBConn();
           Connection ct=sqlsrvdb.getConn();
           ArrayList al=new ArrayList();
           UserTable users = (UserTable)session.getAttribute("user");
                     String username = users.getUsername();
        	 try{
        		 String sql="select * from BookTable";
            	 ResultSet rs=sqlsrvdb.executeQuery(sql);
            	 while(rs.next()){
            		 BookTable gt=new BookTable();
            		 String bookname=rs.getString(2);
            		 String price=rs.getString(3);
            		 String type=rs.getString(4);
            	%>
            <tr>
               <td><%=bookname%></td>
               <td><%=price%></td>
               <td><%=type %></td>
               <td><form action="servlet/buyServlet" method="post">
               <input type="hidden" name="username" value="<%=username%>"/>
               <input type="hidden" name="bookname" value="<%=bookname%>"/>
               <input type="hidden" name="price" value="<%=price%>"/>
               <input type="hidden" name="type" value="<%=type%>"/>
               <input type="submit" value="购买"/>
               </form></td></tr>
               
             <% } %>
             </table>
             <%
            	 rs.close();
        	 }catch(SQLException e){
        		 e.printStackTrace();
        	 };
             %>
        </table>
     
  </body>
</html>
