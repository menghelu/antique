<%@ page language="java" import="java.util.*,yanshuo_1.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mycomputer_1.jsp' starting page</title>
    
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
  <%request.setCharacterEncoding("utf-8"); %>
   <jsp:useBean id="yan" scope="request" class="yanshuo_1.supercomputer_1"/>
   <jsp:setProperty property="*" name="yan"/>
    <%
       String firstnum=request.getParameter("first");
       String secondnum=request.getParameter("second");
       String selector=request.getParameter("select");
       yan.setFirstNum(firstnum);
       yan.setSecondNum(secondnum);
       yan.setOperator(selector);
       yan.yan_computer();
       %>
--------------------------------------------------------<br>
       计算结果是：  <%=yan.getFirstNum()+yan.getOperator()+yan.getSecondNum()+"="+yan.getResult()%><br>
--------------------------------------------------------<br>
<table border=1>
       <tr><td>简单的计算器</td></tr>
       <tr><td>第一个参数:</td>
           <td><input type="text" name="first"/></td></tr>
       <tr><td>操作符</td><td><select name="select">
        <option>+</option>
        <option>-</option>
        <option>*</option>
        <option>/</option>
       </select>
       </td></tr>
       <tr><td>第二个参数:</td>
           <td><input type="text" name="second" /></td></tr>
     <tr><td><input type="submit" value="计算"/></td></tr>
      </table>
  </body>
</html>
