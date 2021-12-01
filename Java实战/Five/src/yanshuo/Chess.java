package yanshuo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class Chess {

	public static final int DIAMETER = ChessBoard.SPAN-2;  //棋子的直径
	private int col;   // 棋子在棋盘中的x索引
	private int row;   // 棋子在棋盘中的y索引
	private Color color;//棋子的颜色
	ChessBoard cb;     //棋子所在的棋盘
	
	public Chess(ChessBoard cb,int col,int row,Color color){
		this.cb = cb;
		this.col = col;
		this.row = row;
		this.color = color;
	}
	
	public int getCol(){
		return col;
	}
	
	public int getRow(){
		return row;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void draw(Graphics g){
		int xPos = col*cb.SPAN+cb.MARGIN;
		int yPos = row*cb.SPAN+cb.MARGIN;
		Graphics2D g2d = (Graphics2D)g;
		//Graphics2D 类扩展Graphics 类，以提供对几何形状、坐标转换、颜色管理和文本布局更为复杂的控制。
		//它是用于在Java 平台上呈现二维形状、文本和图像的基础类。
		RadialGradientPaint paint = null;
		//类提供使用圆形辐射颜色渐变模式填充某一形状的方式
		int x = xPos+DIAMETER/4;
		int y = yPos-DIAMETER/4;
		float[] f = {0f,1f};  //后面的f仅代表是float类型
		Color[] c = {Color.WHITE,Color.BLACK};
		if(color==Color.black){
			paint = new RadialGradientPaint(x,y,DIAMETER/8,f,c);
			//参数分别为填充中心的坐标，渐变范围圆的半径，0表示中心位置，代表白色，1代表圆周位置，代表黑色，代表这一个渐变过程
		}else if(color==Color.white){
			paint = new RadialGradientPaint(x,y,DIAMETER*2,f,c);
		}
		g2d.setPaint(paint);
		//以下两行使棋子的边界绘制更光滑
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
		Ellipse2D e = new Ellipse2D.Float(xPos-DIAMETER/2, yPos-DIAMETER/2, DIAMETER, DIAMETER);
		//其构造函数的参数分别是椭圆外切矩形的左上角坐标和外切矩形的长与宽
		g2d.fill(e);
	}
}
