package yanshuo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


           // 19X19的棋盘
public class ChessBoard extends JPanel {
	public static final int MARGIN = 15;     //边距
	public static final int SPAN = 20;		 //网格宽度
	public static final int ROWS = 18;		 //棋盘行数
	public static final int COLS = 18;		 //棋盘列数
	Chess[] chessList = new Chess[1000]; //记录已经下在棋盘上的棋子
	int chessCount;    //当前棋盘上棋子的个数
	boolean isBlack = true; //下一步轮到哪一方开始下起，默认黑方先手
	boolean isGaming = true;//是否正在游戏
	Image img;
	
    public ChessBoard(){
    	img = Toolkit.getDefaultToolkit().getImage("img/board.jpg");
    	//Toolkit 的子类被用于将各种组件绑定到特定本机工具包实现。
       //拿到默认工具包，通过工具包的方法把硬盘上的图片拿到内存中来
    	this.addMouseListener(new MouseMonitor());
    	this.addMouseMotionListener(new MouseMotionMonitor()); 
    }
    
    //画棋盘
    public void paintComponent(Graphics g){     
    	super.paintComponent(g);   //绘制JPanel容器中的组件
    	g.drawImage(img,0,0,this); //0,0是这个img的左上角在容器的坐标，其实还可以再加width和height
    	//this当作观察类存在，当g的图片更改的时候，通知这个类，调用this的imageUpdate方法。
    	
    	for(int i=0;i<=ROWS;i++){      //画横线
    		g.drawLine(MARGIN, MARGIN+i*SPAN, MARGIN+COLS*SPAN, MARGIN+i*SPAN);
    		//在两点之间画一条直线，前两个参数是起点的横纵坐标
    	}
    	
    	for(int i=0;i<=COLS;i++){
    		g.drawLine(MARGIN+i*SPAN, MARGIN, MARGIN+i*SPAN, MARGIN+ROWS*SPAN);
    	}
    	
    	//画出9个小的黑色正方形
    	g.fillRect(MARGIN+3*SPAN-2, MARGIN+3*SPAN-2, 5, 5);
    	//要填充的矩形的左上方的xy坐标和矩形的宽度和高度
    	g.fillRect(MARGIN+(COLS/2)*SPAN-2, MARGIN+3*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+(COLS-3)*SPAN-2, MARGIN+3*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+3*SPAN-2, MARGIN+(ROWS/2)*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+(COLS/2)*SPAN-2, MARGIN+(ROWS/2)*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+(COLS-3)*SPAN-2, MARGIN+(ROWS/2)*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+3*SPAN-2, MARGIN+(ROWS-3)*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+(COLS/2)*SPAN-2, MARGIN+(ROWS-3)*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+(COLS-3)*SPAN-2, MARGIN+(ROWS-3)*SPAN-2, 5, 5);
    	//测试棋子效果
    	//Chess c1 = new Chess(this,7,6,Color.BLACK);
    	//Chess c2 = new Chess(this,7,7,Color.WHITE);
    	//c1.draw(g);
    	//c2.draw(g);
    	for(int i=0;i<chessCount;i++){
    		chessList[i].draw(g);
    		if(i==chessCount-1){    //如果是最后一个棋子
    			int xPos = chessList[i].getCol()*SPAN+MARGIN;
    			int yPos = chessList[i].getRow()*SPAN+MARGIN;
    			g.setColor(Color.red);
    			g.drawRect(xPos-Chess.DIAMETER/2, yPos-Chess.DIAMETER/2, Chess.DIAMETER, Chess.DIAMETER);
    			//画一个矩形，参数分别为矩形左上顶点的坐标和矩形的长和宽
    		}
    	}
    }
    
    public Dimension getPreferredSize(){
    	// dimension是Java的一个类，封装了一个构件的高度和宽度
    	//这个方法返回棋盘最合适的尺寸
    	return new Dimension(MARGIN*2+SPAN*COLS,MARGIN*2+SPAN*ROWS);
    }
    
    //检验该位置是否已有棋子
    private boolean hasChess(int col,int row){
    	for(int i=0;i<chessCount;i++){
    		Chess ch = chessList[i];
    		if(ch!=null&&ch.getCol()==col&&ch.getRow()==row)
    			return true;
    	}
    	return false;
    }
    
    class MouseMonitor extends MouseAdapter{
    	public void mousePressed(MouseEvent e){
    		//将鼠标单击的像素坐标转换成网格索引
    		int col = (e.getX()-MARGIN+SPAN/2)/SPAN;
    	    int row = (e.getY()-MARGIN+SPAN/2)/SPAN;
    	    //落在棋盘外不能下棋
    	    if(col<0||col>COLS||row<0||row>ROWS)
    	    	return ;
    	    //该位置已有棋子，则不能下棋
    	    if(hasChess(col,row)||!isGaming)
    	    	return ;
    	    Chess ch = new Chess(ChessBoard.this,col,row,isBlack?Color.black:Color.white);
    	    //this代表的是内部类的this，所以用ChessBoard.this
    	    chessList[chessCount++] = ch;
    	    repaint(); //通知系统重新绘制
    	    if(isWin(col,row)){
    	    	String colorName = isBlack?"黑棋":"白棋";
    	    	String msg = String.format("%s胜!", colorName);
    	    	JOptionPane.showMessageDialog(ChessBoard.this, msg);
    	    	//消息提示框
    	    	isGaming = false;
    	    }
    	    isBlack = !isBlack;
    	    if(!isGaming)
    	    	return ;
    	}
    }
    
    class MouseMotionMonitor extends MouseMotionAdapter{
    	public void mouseMoved(MouseEvent e){
    		int col = (e.getX()-MARGIN+SPAN/2)/SPAN;
    		int row = (e.getY()-MARGIN+SPAN/2)/SPAN;
    		if(col<0||col>COLS||row<0||row>ROWS||!isGaming||hasChess(col,row))
    			ChessBoard.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		else
    			ChessBoard.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	}
    }
    
    public void restartGame(){
    	//清除棋子
    	for(int i=0;i<chessList.length;i++){
    		chessList[i]=null;
    	}
    	//恢复游戏相关的默认设置
    	isBlack = true;
    	isGaming = true;
    	chessCount = 0;
    	repaint();
    }
    
    public void goback(){
    	if(chessCount==0){
    		return ;
    		//如果棋子为0，将不能悔棋
    	}
    	chessList[chessCount-1] = null;
    	chessCount--;
    	isBlack = !isBlack;
    	repaint();
    }
    
    //判断这个点上有没有这种颜色的棋子
    private boolean hasChess(int col,int row,Color color){
    	for(int i=0;i<chessCount;i++){
    		Chess ch = chessList[i];
    		if(ch!=null&&ch.getCol()==col&&ch.getRow()==row&&ch.getColor()==color)
    			return true;
    	}
    	return false;
    }
    
    private boolean isWin(int col,int row){
    	int continueCount = 1;
    	Color c = isBlack?Color.black:Color.white;
    	//横向
    	for(int x=col-1;x>=0;x--){
    		if(hasChess(x,row,c))
    			continueCount++;
    		else
    			break;
    	}
    	for(int x=col+1;x<=COLS;x++){
    		if(hasChess(x,row,c))
    			continueCount++;
    		else
    			break;
    	}
    	if(continueCount>=5)
    		return true;
    	else
    		continueCount=1;
    	//纵向
    	for(int y=row-1;y>=0;y--){
    		if(hasChess(col,y,c))
    			continueCount++;
    		else
    			break;
    	}
    	
    	for(int y=row+1;y<=COLS;y++){
    		if(hasChess(col,y,c))
    			continueCount++;
    		else
    			break;
    	}
    	if(continueCount>=5)
    		return true;
    	else
    		continueCount=1;
    //从右上到左下
    for(int x=col+1,y=row-1;y>=0&&x<=COLS;x++,y--){
    	if(hasChess(x,y,c))
    		continueCount++;
    	else
    		break;
    }
    for(int x=col-1,y=row+1;x>=0&&y<=ROWS;x--,y++){
    	if(hasChess(x,y,c))
    		continueCount++;
    	else
    		break;
    }
    if(continueCount>=5)
		return true;
	else
		continueCount=1;
   //从左上到右下
    for(int x=col-1,y=row-1;x>=0&&y>=0;x--,y--){
    	if(hasChess(x,y,c))
    		continueCount++;
    	else
    		break;
    }
    for(int x=col+1,y=row+1;x<=COLS&&y<=ROWS;x++,y++){
    	if(hasChess(x,y,c))
    		continueCount++;
    	else
    		break;
    }
    if(continueCount>=5)
		return true;
	else
		return false;
  }
}
