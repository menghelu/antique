package Servlet1;

import java.io.IOException;
import java.io.*;

import JDBC1.SqlSrvDBConn;
import JavaBean.*;

import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.*;

import java.util.*;

public class Main extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Main() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String usr=request.getParameter("username");
		String pwd=request.getParameter("password");
		boolean validated=false;
		SqlSrvDBConn sqlsrvdb=new SqlSrvDBConn();          //实例化，与数据库建立连接
		HttpSession session=request.getSession();           //获取会话对象，用来保存当前登录用户的信息
		UserTable user=null;   
		//先获得usertable对象，若第一次登录则对象肯定为null，若再登陆。就直接登录主页，无需重复验证
		user=(UserTable)session.getAttribute("user");
		//如果第一次登录，会话中尚未储存user持久化对象，故为null
		if(user==null){
			//查询Table中的记录
			String sql="select* from userTable";
			ResultSet rs=sqlsrvdb.executeQuery(sql);
			try{
				while(rs.next()){
					if((rs.getString("username").trim().compareTo(usr)==0)
							&&(rs.getString("password").compareTo(pwd)==0))
					  {
						user=new UserTable();
						user.setId(rs.getInt(1));
						user.setUsername(rs.getString(2));
						user.setPassword(rs.getString(3));
						session.setAttribute("user",user);     //把user对象存储在session中
						validated=true;
					  }
				                }
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			sqlsrvdb.closeStmt();
		}
		else {
			validated=true;
		}
		if(validated){//验证成功，应该去主界面，主界面中包含了所有留言信息，所以要从留言表中查出来，并暂存在会话中
			ArrayList al=new ArrayList();
			try{
				String sql="select* from lyTable";
				ResultSet rs=sqlsrvdb.executeQuery(sql);
				while(rs.next())
				{
					LyTable ly=new LyTable();
					//获取留言信息
					ly.setId(rs.getInt(1));
					ly.setUserID(rs.getInt(2));
					ly.setDate(rs.getDate(3));
					ly.setTitle(rs.getString(4));
					ly.setContent(rs.getString(5));
					al.add(ly);                       //添加如留言信息列表
				} 
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			sqlsrvdb.closeStmt();
			session.setAttribute("al",al);  //留言存入会话
			//然后跳转到main.jsp
			response.sendRedirect("main.jsp");
		}
		else{
			//验证失败跳转到error.jsp
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	           doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */

}
