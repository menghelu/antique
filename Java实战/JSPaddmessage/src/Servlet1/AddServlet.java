package Servlet1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.*;

import JDBC1.SqlSrvDBConn;
import JavaBean.*;

public class AddServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddServlet() {
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
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		HttpSession session=request.getSession();           //��ȡ��������
		UserTable user=(UserTable)session.getAttribute("user");
		user=(UserTable)session.getAttribute("user");
		//�������Ա��Ӧ��JavaBean���󣬰����ݷ�װ��ȥ
		LyTable ly=new LyTable();
		ly.setUserID(user.getID());
		ly.setDate(new Date(System.currentTimeMillis()));
		ly.setTitle(title);
		ly.setContent(content);
		ArrayList al=(ArrayList)session.getAttribute("al");
		al.add(ly);//����ӵ�����Ҫ����һ���ڻỰ�У�������ˢ����ҳʱ�Ͳ���ÿ�ζ���ѯ���ݿ����Ա���
		//�����ݿ�����µ����Լ�¼
		PreparedStatement pstmt=null;
		SqlSrvDBConn sqlsrvdb=new SqlSrvDBConn();
		Connection ct=sqlsrvdb.getConn();
			try{
				pstmt=ct.prepareStatement("insert into lyTable values(?,?,?,?)");
				pstmt.setInt(1,ly.getUserID());
				pstmt.setDate(2,ly.getDate());
				pstmt.setString(3,ly.getTitle());
				pstmt.setString(4,ly.getContent());
				pstmt.executeUpdate();
				response.sendRedirect("main.jsp");
			}catch(SQLException e){
				e.printStackTrace();
				response.sendRedirect("bbs.jsp");
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
	public void init() throws ServletException {
		// Put your code here
	}

}
