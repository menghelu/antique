package yanshuo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import yanshuo.ChessBoard.MouseMonitor;


public class Five extends JFrame {  //JFrame ��ܴ���(��������)  �̳�
	private JToolBar toolbar;    //������(�м������)
	private JButton startButton,backButton,exitButton; //ԭ�����
	private ChessBoard boardPanel;
    JCheckBox computerFirst;
	
	public Five(){
		super("��������");//���ø���Ĺ��췽��
		// public JFrame(String title) ����һ�����б���Ķ�������
		toolbar = new JToolBar();
		startButton = new JButton("���¿�ʼ");
		backButton = new JButton("����");
		exitButton = new JButton("�˳�");
		computerFirst = new JCheckBox("�������");
		toolbar.add(startButton);
		toolbar.add(backButton);
		toolbar.add(exitButton);
		toolbar.add(computerFirst);
		this.add(toolbar,BorderLayout.NORTH);
		boardPanel = new ChessBoard(this);
		this.add(boardPanel,BorderLayout.CENTER);
		this.setLocation(200, 200);   //���ô��ڵ����Ͻ�λ��
		this.pack();//�������������ʹ���ڵĴ�С��������Ĵ�С�Զ�����
		this.setResizable(false);//ʹ���ڵĴ�С���ɸı�
		//this.setBounds(200,200,300,200);
		//����ܵ����Ͻ�������Ļ����(x,y)������ܵĿ���ܵĸ�
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//���ùرմ��ڲ��� ��Ĭ��ֵ       ->�����������ڵ�Ӧ�ó���
		this.setVisible(true);//����Ļ���Ƿ���ʾ���
		//Ϊ��ťע�����
		ActionMonitor monitor = new ActionMonitor();
		startButton.addActionListener(monitor);
		backButton.addActionListener(monitor);
		exitButton.addActionListener(monitor);
	}

    //����ڲ���������
	class ActionMonitor implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==startButton){
				boardPanel.restartGame();
			}else if(e.getSource()==backButton){
				boardPanel.goback();
			}else if(e.getSource()==exitButton){
				System.exit(0);
			}
		}
	}
	public static void main(String[] args) {
		
		new Five();
	}

}
