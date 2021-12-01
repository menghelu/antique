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


           // 15X15������
public class ChessBoard extends JPanel {
	public static final int MARGIN = 15;     //�߾�
	public static final int SPAN = 20;		 //������
	public static final int ROWS = 14;		 //��������
	public static final int COLS = 14;		 //��������
	Chess[] chessList = new Chess[1000]; //��¼�Ѿ����������ϵ�����
	int chessCount;    //��ǰ���������ӵĸ���
	boolean isBlack; //��һ���Ƿ�ú�������
	boolean isGaming = false;//�Ƿ�������Ϸ
	Image img;
	int computerColor;  //�����������ɫ��1:����  2:����
	boolean isComputerGo;  //�Ƿ�ü��������
	private Five f;   //Five���������
	int[][] boardStatus;  //0:������  1:���� 2:����
	
    public ChessBoard(Five f){
    	this.f=f;
    	boardStatus = new int[COLS+1][ROWS+1];
    	for(int i=0;i<=COLS;i++){
    		for(int j=0;j<=ROWS;j++){
    			boardStatus[i][j] = 0;
    		}
    	}
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
    		if(!isGaming)
    			return ;
    		if(isComputerGo)
    			return ;
    		//����굥������������ת������������
    		int col = (e.getX()-MARGIN+SPAN/2)/SPAN;
    	    int row = (e.getY()-MARGIN+SPAN/2)/SPAN;
    	    //���������ⲻ������
    	    if(col<0||col>COLS||row<0||row>ROWS)
    	    	return ;
    	    //��λ���������ӣ���������
    	    if(hasChess(col,row))
    	    	return ;
    	    manGo(col,row);
    	    if(!isGaming)
    	    	return ;
    	    computerGo();
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
    	for(int i=0;i<=COLS;i++){
    		for(int j=0;j<=ROWS;j++){
    			boardStatus[i][j] = 0;
    		}
    	}
    	//�ָ���Ϸ��ص�Ĭ������
    	isBlack = true;
    	isGaming = true;
    	isComputerGo = f.computerFirst.isSelected();
    	computerColor = isComputerGo?1:2; //�����������У����ú���
    	chessCount = 0;
    	if(isComputerGo){
    		computerGo();  //�����������У����������һ��
    	}
    	paintComponent(this.getGraphics());
    	//repaint();
    	//ֱ�ӵ���paintComponent��������repaint����ˢ�½����ԭ���ǣ�������ʱ���ܼ�ʱˢ��ҳ��
    }
    
    private void computerGo(){
    	Evaluate e = new Evaluate(this);
    	int pos[] = e.getTheBestPosition();
    	putChess(pos[0],pos[1],isBlack?Color.black:Color.white);
    }
   
    private void manGo(int col,int row){
    	putChess(col,row,isBlack?Color.black:Color.white);
    }
    
    public void putChess(int col,int row,Color color){
    	Chess ch = new Chess(ChessBoard.this,col,row,color);
    	//this��������ڲ����this��������ChessBoard.this
    	chessList[chessCount++] = ch;
    	boardStatus[col][row] = (color==Color.black)?1:2;
    	paintComponent(this.getGraphics());
    	if(isWin(col,row)){
    		String colorName = isBlack?"����":"����";
    		String msg = String.format("��ϲ��%sӮ�ˣ�",colorName);
    		JOptionPane.showMessageDialog(ChessBoard.this,msg); //��Ϣ��ʾ��
    		isGaming = false;
    	}else{
    		isBlack = !isBlack;
    		isComputerGo = !isComputerGo;
    	}
    }
    
    public void goback(){
    	//����ֵ�������������������С��2�����ܻ���
    	if(isComputerGo||chessCount<2)
    		return ;
    	int i = chessList[chessCount-1].getCol();
    	int j = chessList[chessCount-1].getRow();
    	boardStatus[i][j] = 0;
    	i = chessList[chessCount-2].getCol();
    	j = chessList[chessCount-2].getRow();
    	boardStatus[i][j] = 0;
    	chessList[chessCount-1] = null;
    	chessList[chessCount-2] = null;
    	chessCount-=2;
    	paintComponent(this.getGraphics());
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
