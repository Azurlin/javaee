package com.Tfishe.day01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.awt.AWTUtilities;

public class GameFrame extends JFrame implements ActionListener,MouseListener {
	//用于存背景
	JLabel label;
	JButton min;
	JButton close;
	GameJPanel gamepanel;
	static GameFrame gameframe ;//全局变量也叫做属性
	private boolean isDraging = false;//判断鼠标是否按下
	private int X,Y;
	public static void main(String[] args) {
		//静态调用非静态 通过对象的方式
		gameframe = new GameFrame();
		gameframe.showMe();
	}
	//创建窗口并初始化
	public void showMe(){
		
		//设置标题
		this.setTitle("捕鱼达人");
		//设置窗体位置和大小
		this.setBounds(200, 100, 880, 580);
		//禁止窗体装饰
		this.setUndecorated(true);
		//透明化窗体
		AWTUtilities.setWindowOpaque(this, false);
		//设置图标
		this.setIconImage(new ImageIcon("images/icon.jpg").getImage());

		//实现窗口拖动 添加拖拽接口 参数传入的是接口的实现类 适配器
		this.addMouseListener(this);
		this.addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseDragged(MouseEvent e) {
				if(isDraging){//判断是否按下鼠标
					int left = GameFrame.this.getLocation().x;
					int top = GameFrame.this.getLocation().y;
					GameFrame.this.setLocation(left + (e.getX() - X), top+(e.getY()-Y));
					
				}
			}
		});
		//背景图片一般放在label中
		label = new JLabel(new ImageIcon("images/bg.png"));
		
		gamepanel = new GameJPanel(800,480);
		//设置位置和大小
		gamepanel.setBounds(40,65,800,480);
		
		
		//背景图片一般放在label中
		label = new JLabel(new ImageIcon("images/bg.png"));
		
		//创建最小化按钮
		min = new JButton(new ImageIcon("images/btn-2.png"));
		//创建关闭按钮
		close = new JButton(new ImageIcon("images/btn-4.png"));		
		min.setBounds(772, 28, 32, 32);
		//设置边框
		min.setBorderPainted(false);
		//设置图片是否充满label
		min.setContentAreaFilled(false);
		
		//对min添加事件源
		min.addActionListener(this);
		//匿名内部类
		min.addMouseListener( new MouseAdapter(){
			//鼠标进入
			@Override
			public void mouseEntered(MouseEvent e) {
				min.setIcon(new ImageIcon("images/btn-1.png"));
			}
			//鼠标移出
			@Override
			public void mouseExited(MouseEvent e) {
				min.setIcon(new ImageIcon("images/btn-2.png"));
			}
		});	
				
		//设置关闭按钮位置大小
		close.setBounds(810, 28, 32, 32);
		//设置边框
		close.setBorderPainted(false);
		//设置图片是否充满label
		close.setContentAreaFilled(false);
		close.addActionListener(this);
		//匿名内部类
		close.addMouseListener( new MouseAdapter(){
			//鼠标进入
			@Override
			public void mouseEntered(MouseEvent e) {
				
				close.setIcon(new ImageIcon("images/btn-3.png"));
			}
			//鼠标移出
			@Override
			public void mouseExited(MouseEvent e) {
				close.setIcon(new ImageIcon("images/btn-4.png"));
			}
		});	
		
		gamepanel. startFish();
		
		label.add(gamepanel);
		label.add(min);
		label.add(close);
		this.add(label);
		this.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == min){
			gameframe.setState(JFrame.ICONIFIED);
		}else if(e.getSource() == close){
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		isDraging = true;//标记已经被按下
		X = e.getX();
		Y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		isDraging = false;
	}

}
