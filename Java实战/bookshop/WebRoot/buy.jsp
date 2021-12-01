<%@ page language="java" import="java.util.*,java.sql.*,chj.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'buy.jsp' starting page</title>
    
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
   <form action="main.jsp" method="post">
     <table>
        <caption>已买书籍</caption>
        <tr>
           <th>书籍名字</th><th>价格</th><th>类型</th>
        </tr>
           <%
           PreparedStatement pstmt=null;
           SqlSrvDBConn sqlsrvdb=new SqlSrvDBConn();
           Connection ct1=sqlsrvdb.getConn();
           UserTable user = (UserTable)session.getAttribute("user");
           String username = user.getUsername();
               try{
              String sql="select * from mine where username ="+"'"+username+"'";
                    pstmt=ct1.prepareStatement(sql);
                    ResultSet rs=pstmt.executeQuery();
                     while(rs.next()){
                     String id=rs.getString(1);
                     String goodsname=rs.getString(2);
                     String price=rs.getString(3);
                     String type=rs.getString(4);   
            %>
            <tr>
               <td><%=goodsname%></td>
               <td><%=price%></td>
               <td><%=type%></td>
            </tr>
            <%
                         }
                   }catch(SQLException e){
                          e.printStackTrace();
                   }
             %>
        
       </table>
       <input type="submit" name="" value="点此继续购买"/>
     </form>
  
  </body>
</html>
