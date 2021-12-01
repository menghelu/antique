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
		SqlSrvDBConn sqlsrvdb=new SqlSrvDBConn();          //ʵ�����������ݿ⽨������
		HttpSession session=request.getSession();           //��ȡ�Ự�����������浱ǰ��¼�û�����Ϣ
		UserTable user=null;   
		//�Ȼ��usertable��������һ�ε�¼�����϶�Ϊnull�����ٵ�½����ֱ�ӵ�¼��ҳ�������ظ���֤
		user=(UserTable)session.getAttribute("user");
		//�����һ�ε�¼���Ự����δ����user�־û����󣬹�Ϊnull
		if(user==null){
			//��ѯTable�еļ�¼
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
						session.setAttribute("user",user);     //��user����洢��session��
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
		if(validated){//��֤�ɹ���Ӧ��ȥ�����棬�������а���������������Ϣ������Ҫ�����Ա��в���������ݴ��ڻỰ��
			ArrayList al=new ArrayList();
			try{
				String sql="select* from lyTable";
				ResultSet rs=sqlsrvdb.executeQuery(sql);
				while(rs.next())
				{
					LyTable ly=new LyTable();
					//��ȡ������Ϣ
					ly.setId(rs.getInt(1));
					ly.setUserID(rs.getInt(2));
					ly.setDate(rs.getDate(3));
					ly.setTitle(rs.getString(4));
					ly.setContent(rs.getString(5));
					al.add(ly);                       //�����������Ϣ�б�
				} 
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			sqlsrvdb.closeStmt();
			session.setAttribute("al",al);  //���Դ���Ự
			//Ȼ����ת��main.jsp
			response.sendRedirect("main.jsp");
		}
		else{
			//��֤ʧ����ת��error.jsp
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
