package yanshuo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class deleteBook_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public deleteBook_Servlet() {
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
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String sql = "delete from book where id = ?";
		Connection conn = database.get_connection();
		ResultSet rs = null;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,id);
			rs = ps.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			database.closeConn(conn, null, rs);
		}
	 	response.sendRedirect("../manager_show.jsp");
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
		String id1 = request.getParameter("id");
		int id =Integer.parseInt(id1);
		String sql = "delete from book where id = ?";
		Connection conn = database.get_connection();
		ResultSet rs = null;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			rs = ps.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			database.closeConn(conn, null, rs);
		}
	 	response.sendRedirect("../manager_show.jsp");
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
