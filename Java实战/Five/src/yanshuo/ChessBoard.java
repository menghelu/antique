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


           // 19X19������
public class ChessBoard extends JPanel {
	public static final int MARGIN = 15;     //�߾�
	public static final int SPAN = 20;		 //������
	public static final int ROWS = 18;		 //��������
	public static final int COLS = 18;		 //��������
	Chess[] chessList = new Chess[1000]; //��¼�Ѿ����������ϵ�����
	int chessCount;    //��ǰ���������ӵĸ���
	boolean isBlack = true; //��һ���ֵ���һ����ʼ����Ĭ�Ϻڷ�����
	boolean isGaming = true;//�Ƿ�������Ϸ
	Image img;
	
    public ChessBoard(){
    	img = Toolkit.getDefaultToolkit().getImage("img/board.jpg");
    	//Toolkit �����౻���ڽ���������󶨵��ض��������߰�ʵ�֡�
       //�õ�Ĭ�Ϲ��߰���ͨ�����߰��ķ�����Ӳ���ϵ�ͼƬ�õ��ڴ�����
    	this.addMouseListener(new MouseMonitor());
    	this.addMouseMotionListener(new MouseMotionMonitor()); 
    }
    
    //������
    public void paintComponent(Graphics g){     
    	super.paintComponent(g);   //����JPanel�����е����
    	g.drawImage(img,0,0,this); //0,0�����img�����Ͻ������������꣬��ʵ�������ټ�width��height
    	//this�����۲�����ڣ���g��ͼƬ���ĵ�ʱ��֪ͨ����࣬����this��imageUpdate������
    	
    	for(int i=0;i<=ROWS;i++){      //������
    		g.drawLine(MARGIN, MARGIN+i*SPAN, MARGIN+COLS*SPAN, MARGIN+i*SPAN);
    		//������֮�仭һ��ֱ�ߣ�ǰ�������������ĺ�������
    	}
    	
    	for(int i=0;i<=COLS;i++){
    		g.drawLine(MARGIN+i*SPAN, MARGIN, MARGIN+i*SPAN, MARGIN+ROWS*SPAN);
    	}
    	
    	//����9��С�ĺ�ɫ������
    	g.fillRect(MARGIN+3*SPAN-2, MARGIN+3*SPAN-2, 5, 5);
    	//Ҫ���ľ��ε����Ϸ���xy����;��εĿ�Ⱥ͸߶�
    	g.fillRect(MARGIN+(COLS/2)*SPAN-2, MARGIN+3*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+(COLS-3)*SPAN-2, MARGIN+3*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+3*SPAN-2, MARGIN+(ROWS/2)*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+(COLS/2)*SPAN-2, MARGIN+(ROWS/2)*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+(COLS-3)*SPAN-2, MARGIN+(ROWS/2)*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+3*SPAN-2, MARGIN+(ROWS-3)*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+(COLS/2)*SPAN-2, MARGIN+(ROWS-3)*SPAN-2, 5, 5);
    	g.fillRect(MARGIN+(COLS-3)*SPAN-2, MARGIN+(ROWS-3)*SPAN-2, 5, 5);
    	//��������Ч��
    	//Chess c1 = new Chess(this,7,6,Color.BLACK);
    	//Chess c2 = new Chess(this,7,7,Color.WHITE);
    	//c1.draw(g);
    	//c2.draw(g);
    	for(int i=0;i<chessCount;i++){
    		chessList[i].draw(g);
    		if(i==chessCount-1){    //��������һ������
    			int xPos = chessList[i].getCol()*SPAN+MARGIN;
    			int yPos = chessList[i].getRow()*SPAN+MARGIN;
    			g.setColor(Color.red);
    			g.drawRect(xPos-Chess.DIAMETER/2, yPos-Chess.DIAMETER/2, Chess.DIAMETER, Chess.DIAMETER);
    			//��һ�����Σ������ֱ�Ϊ�������϶��������;��εĳ��Ϳ�
    		}
    	}
    }
    
    public Dimension getPreferredSize(){
    	// dimension��Java��һ���࣬��װ��һ�������ĸ߶ȺͿ��
    	//�������������������ʵĳߴ�
    	return new Dimension(MARGIN*2+SPAN*COLS,MARGIN*2+SPAN*ROWS);
    }
    
    //�����λ���Ƿ���������
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
    		//����굥������������ת������������
    		int col = (e.getX()-MARGIN+SPAN/2)/SPAN;
    	    int row = (e.getY()-MARGIN+SPAN/2)/SPAN;
    	    //���������ⲻ������
    	    if(col<0||col>COLS||row<0||row>ROWS)
    	    	return ;
    	    //��λ���������ӣ���������
    	    if(hasChess(col,row)||!isGaming)
    	    	return ;
    	    Chess ch = new Chess(ChessBoard.this,col,row,isBlack?Color.black:Color.white);
    	    //this��������ڲ����this��������ChessBoard.this
    	    chessList[chessCount++] = ch;
    	    repaint(); //֪ͨϵͳ���»���
    	    if(isWin(col,row)){
    	    	String colorName = isBlack?"����":"����";
    	    	String msg = String.format("%sʤ!", colorName);
    	    	JOptionPane.showMessageDialog(ChessBoard.this, msg);
    	    	//��Ϣ��ʾ��
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
    	//�������
    	for(int i=0;i<chessList.length;i++){
    		chessList[i]=null;
    	}
    	//�ָ���Ϸ��ص�Ĭ������
    	isBlack = true;
    	isGaming = true;
    	chessCount = 0;
    	repaint();
    }
    
    public void goback(){
    	if(chessCount==0){
    		return ;
    		//�������Ϊ0�������ܻ���
    	}
    	chessList[chessCount-1] = null;
    	chessCount--;
    	isBlack = !isBlack;
    	repaint();
    }
    
    //�ж����������û��������ɫ������
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
    	//����
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
    	//����
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
    //�����ϵ�����
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
   //�����ϵ�����
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
