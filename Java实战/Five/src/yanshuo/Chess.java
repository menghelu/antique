package yanshuo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class Chess {

	public static final int DIAMETER = ChessBoard.SPAN-2;  //���ӵ�ֱ��
	private int col;   // �����������е�x����
	private int row;   // �����������е�y����
	private Color color;//���ӵ���ɫ
	ChessBoard cb;     //�������ڵ�����
	
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
		//Graphics2D ����չGraphics �࣬���ṩ�Լ�����״������ת������ɫ������ı����ָ�Ϊ���ӵĿ��ơ�
		//����������Java ƽ̨�ϳ��ֶ�ά��״���ı���ͼ��Ļ����ࡣ
		RadialGradientPaint paint = null;
		//���ṩʹ��Բ�η�����ɫ����ģʽ���ĳһ��״�ķ�ʽ
		int x = xPos+DIAMETER/4;
		int y = yPos-DIAMETER/4;
		float[] f = {0f,1f};  //�����f��������float����
		Color[] c = {Color.WHITE,Color.BLACK};
		if(color==Color.black){
			paint = new RadialGradientPaint(x,y,DIAMETER/8,f,c);
			//�����ֱ�Ϊ������ĵ����꣬���䷶ΧԲ�İ뾶��0��ʾ����λ�ã������ɫ��1����Բ��λ�ã������ɫ��������һ���������
		}else if(color==Color.white){
			paint = new RadialGradientPaint(x,y,DIAMETER*2,f,c);
		}
		g2d.setPaint(paint);
		//��������ʹ���ӵı߽���Ƹ��⻬
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
		Ellipse2D e = new Ellipse2D.Float(xPos-DIAMETER/2, yPos-DIAMETER/2, DIAMETER, DIAMETER);
		//�乹�캯���Ĳ����ֱ�����Բ���о��ε����Ͻ���������о��εĳ����
		g2d.fill(e);
	}
}
