package wjy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EditServlet() {
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

		String idStr=request.getParameter("id");
		int id=Integer.parseInt(idStr);
		ServletContext application=this.getServletContext();
		UserManagerBean userManager=(UserManagerBean)application.getAttribute("userManager");
		if(userManager==null)
		{
			userManager=new UserManagerBean();
			application.setAttribute("userManager", userManager);
		}
		UserDataBean userData=userManager.get(id);
		request.setAttribute("object_user", userData);
		request.getRequestDispatcher("../edit.jsp").forward(request, response);
		
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

		request.setCharacterEncoding("UTF-8");
		String password=request.getParameter("password");
		String confirm=request.getParameter("confirm");
		
		if(password.equals(confirm))
		{
			UserDataBean data = new UserDataBean();
			data.setUsername(request.getParameter("username"));
			data.setPassword(request.getParameter("password"));
			data.setRealname(request.getParameter("realname"));
			data.setSex(request.getParameter("sex"));
			data.setRole(request.getParameter("role"));
			
			ServletContext application=this.getServletContext();
			UserManagerBean userManager=(UserManagerBean)application.getAttribute("userManager");
			if(userManager==null)
			{
				userManager=new UserManagerBean();
				application.setAttribute("userManager", userManager);
			}
			
			
			
			userManager.modify(data);
			response.sendRedirect("../main.jsp");
		}
		else
		{
			
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
