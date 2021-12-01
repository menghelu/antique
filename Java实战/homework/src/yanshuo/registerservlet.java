package yanshuo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class registerservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public registerservlet() {
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
		String id=request.getParameter("id");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		String sex=request.getParameter("sex");
		String grade=request.getParameter("grade");
		String major=request.getParameter("major");
		if(password.equals(password1)){
			check ch=new check();
			boolean flag=ch.checkuser_bool(id);
			if(flag){
				String sql="insert into users(id,username,password,sex,grade,major) values(?,?,?,?,?,?)";
				Connection conn=database.get_connection();
				ResultSet rs=null;
				try{
					PreparedStatement ps=conn.prepareStatement(sql);
					ps.setString(1,id);
					ps.setString(2,username);
					ps.setString(3,password);
					ps.setString(4,sex);
					ps.setString(5,grade);
					ps.setString(6,major);
					rs=ps.executeQuery();
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					database.closeConn(conn,null,rs);
				}
				response.sendRedirect("/homework/register_success.html");
			}else{
				response.sendRedirect("/homework/register_failure.html");
		}
		}else{
			response.sendRedirect("/homework/register_failure1.html");
		}
		

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
