<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
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
  <form action="servlet/loginservlet" method="post">
  <center>
  <table>
      <caption>�ͻ���¼</caption>
      <tr>
         <td>�û�����</td>
         <td>
             <input type=" text" name="username"/>
         </td>
      </tr>
      <tr>
         <td>����:</td>
         <td>
             <input type="password" name="password" />
         </td>
      </tr>
  </table>
  <input type="submit" value="��¼"/>
  <input type="reset" value="����"/></br>
    ���δע���뵥��<a href="register.jsp">����</a>ע�ᣡ
    </center>
  </form>
  </body>
</html>
