package wang;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class updateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public updateServlet() {
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
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		String select = request.getParameter("select");
			String sql;
			if(select.equals("password")){
				sql="update users set password=? where username=?";
			}else if(select.equals("password")){
				sql="update users set password=? where username=?";
			}else if(select.equals("phone")){
				sql="update users set phone=? where username=?";
			}else if(select.equals("dept_id")){
				sql="update users set dept_id=? where username=?";
			}else if(select.equals("realname")){
				sql="update users set realname=? where username=?";
			}else {
				sql="update users set email=? where username=?";
			}
			
			Connection conn=database.get_connection();
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,content);
				ps.setString(2,id);
				rs=ps.executeQuery();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				database.closeConn(conn,null,rs);
			}
			response.sendRedirect("../update.html");
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
