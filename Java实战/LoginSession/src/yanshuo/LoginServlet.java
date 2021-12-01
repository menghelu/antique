package yanshuo;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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
		String op=request.getParameter("op");
		if("exit".equals(op)){
			HttpSession session=request.getSession();
			session.invalidate();
		}
		response.sendRedirect("/LoginSession/login.html");
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
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        
        String saveUsername=request.getParameter("saveUsername");
        if("yes".equals(saveUsername)){
        	Cookie c=new Cookie("username",username);
        	c.setMaxAge(60*60*24*30);
        	c.setPath("/");
        	response.addCookie(c);
        }
        ServletContext application=this.getServletContext();
        UserManager userManager=(UserManager)application.getAttribute("userManager");
        if(userManager==null){
        	userManager=new UserManager();
        	application.setAttribute("userManager", userManager);
        }
        if(userManager.login(username,password)==0){
        	HttpSession session=request.getSession();
        	session.setAttribute("username", username);
        	request.getRequestDispatcher("/main.jsp").forward(request,response);
        }else{
        	request.getRequestDispatcher("/error.html").forward(request, response);
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
