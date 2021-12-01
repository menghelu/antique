package yanshuo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import yanshuo.ChessBoard.MouseMonitor;


public class Five extends JFrame {  //JFrame 框架窗口(顶层容器)  继承
	private JToolBar toolbar;    //工具栏(中间层容器)
	private JButton startButton,backButton,exitButton; //原子组件
	private ChessBoard boardPanel;
    JCheckBox computerFirst;
	
	public Five(){
		super("智障三号");//调用父类的构造方法
		// public JFrame(String title) 创建一个带有标题的顶层容器
		toolbar = new JToolBar();
		startButton = new JButton("重新开始");
		backButton = new JButton("悔棋");
		exitButton = new JButton("退出");
		computerFirst = new JCheckBox("计算机先");
		toolbar.add(startButton);
		toolbar.add(backButton);
		toolbar.add(exitButton);
		toolbar.add(computerFirst);
		this.add(toolbar,BorderLayout.NORTH);
		boardPanel = new ChessBoard(this);
		this.add(boardPanel,BorderLayout.CENTER);
		this.setLocation(200, 200);   //设置窗口的左上角位置
		this.pack();//调用这个方法，使窗口的大小根据组件的大小自动调整
		this.setResizable(false);//使窗口的大小不可改变
		//this.setBounds(200,200,300,200);
		//将框架的左上角移至屏幕坐标(x,y)处，框架的宽，框架的高
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//设置关闭窗口操作 的默认值       ->结束窗口所在的应用程序
		this.setVisible(true);//在屏幕上是否显示框架
		//为按钮注册监听
		ActionMonitor monitor = new ActionMonitor();
		startButton.addActionListener(monitor);
		backButton.addActionListener(monitor);
		exitButton.addActionListener(monitor);
	}

    //添加内部监听器类
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
