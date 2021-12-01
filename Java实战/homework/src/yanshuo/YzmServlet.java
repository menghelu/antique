package yanshuo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class YzmServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public YzmServlet() {
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
		        //����ҳ�治����
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				//���ڴ��д���ͼ��
				int width=60, height=20;
				BufferedImage image = new
				     BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
				//���ͼ��������
				Graphics g =image.getGraphics();
				//���������
				Random random = new Random();
				//����ɫ
				g.setColor(new Color(0,0,0));
				g.fillRect(0, 0, width, height);
				//��������
				g.setFont(new Font("Times New Roman",Font.PLAIN,18));
				for (int i=0;i<200;i++){
					int x=random.nextInt(width);
					int y=random.nextInt(height);
					int x1=random.nextInt(12);
					int y1=random.nextInt(12);
					g.drawLine(x, y, x+x1, y+y1);
				}
				String sRand="";
				for (int i=0;i<4;i++){
					String rand=String.valueOf(random.nextInt(10));
					sRand+=rand;
					g.setColor(new Color(30+random.nextInt(160),
					      40+random.nextInt(170),40+random.nextInt(180)));
					g.drawString(rand, 13*i+6, 16);
				}
				//��YZM����session
				request.getSession().setAttribute("yzm", sRand);
				g.dispose();
				//���ͼ��ҳ��
				ImageIO.write(image, "JPEG",response.getOutputStream());
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
