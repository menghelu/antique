package meng;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class gaiqian_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public gaiqian_servlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		//System.out.println(username);
		//System.out.println("yyy");
		String id3 = request.getParameter("id1");
		String id4 = request.getParameter("id2");
		String departure = null,destination = null,price = null;
		int id1 = Integer.parseInt(id3);
		int id2 = Integer.parseInt(id4);
	String sql="select * from record where username=? and ticket_id=?";
	Connection conn=database.get_connection();
	ResultSet rs=null;
	try{
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1,username);
		ps.setInt(2,id1);
		rs=ps.executeQuery();
		while(rs.next()){
         departure = rs.getString(3);
         destination = rs.getString(4);
         price = rs.getString(5);
 }
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		database.closeConn(conn,null,rs);
	}
	sql = "update record set ticket_id=? ,departure=?,destination=?,price=? where username=? and ticket_id=?";
			 conn=database.get_connection();
	 rs=null;
	try{
		PreparedStatement ps=conn.prepareStatement(sql);
		
		ps.setInt(1,id2);
		ps.setString(2,departure);
		ps.setString(3,destination);
		ps.setString(4,price);
		ps.setString(5,username);
		ps.setInt(6,id1);
		rs=ps.executeQuery();
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		database.closeConn(conn,null,rs);
	}
	response.sendRedirect("../main.jsp");
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
