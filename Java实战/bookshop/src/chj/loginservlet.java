package chj;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chj.SqlSrvDBConn;

public class loginservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public loginservlet() {
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
		 request.setCharacterEncoding("gb2312");
         String usr = request.getParameter("username");
         String pwd = request.getParameter("password");
         SqlSrvDBConn sqlsrvdb=new SqlSrvDBConn();
         HttpSession session=request.getSession();
         UserTable user ;
        	 String sql="select * from userTable";
        	 ResultSet rs=sqlsrvdb.executeQuery(sql);
        	 try{
        		 while(rs.next())
        		 {
        			 if((rs.getString("username").trim().compareTo(usr)==0&&(rs.getString("password").compareTo(pwd)==0))){
        				 user=new UserTable();
        				 user.setUsername(rs.getString(1));
        				 user.setPassword(rs.getString(2));
        				 session.setAttribute("user", user);
        				 response.sendRedirect("../main.jsp");
        				 return;
        			 }
        		 }
        		 rs.close();
        	 }catch(SQLException e){
        		 e.printStackTrace();
        	 }
        	 sqlsrvdb.closeStmt();
        	 response.sendRedirect("../error.jsp");
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
	public void init() throws ServletException {
		// Put your code here
	}

}
