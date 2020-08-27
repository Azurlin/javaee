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
	//���ڴ汳��
	JLabel label;
	JButton min;
	JButton close;
	GameJPanel gamepanel;
	static GameFrame gameframe ;//ȫ�ֱ���Ҳ��������
	private boolean isDraging = false;//�ж�����Ƿ���
	private int X,Y;
	public static void main(String[] args) {
		//��̬���÷Ǿ�̬ ͨ������ķ�ʽ
		gameframe = new GameFrame();
		gameframe.showMe();
	}
	//�������ڲ���ʼ��
	public void showMe(){
		
		//���ñ���
		this.setTitle("�������");
		//���ô���λ�úʹ�С
		this.setBounds(200, 100, 880, 580);
		//��ֹ����װ��
		this.setUndecorated(true);
		//͸��������
		AWTUtilities.setWindowOpaque(this, false);
		//����ͼ��
		this.setIconImage(new ImageIcon("images/icon.jpg").getImage());

		//ʵ�ִ����϶� �����ק�ӿ� ����������ǽӿڵ�ʵ���� ������
		this.addMouseListener(this);
		this.addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseDragged(MouseEvent e) {
				if(isDraging){//�ж��Ƿ������
					int left = GameFrame.this.getLocation().x;
					int top = GameFrame.this.getLocation().y;
					GameFrame.this.setLocation(left + (e.getX() - X), top+(e.getY()-Y));
					
				}
			}
		});
		//����ͼƬһ�����label��
		label = new JLabel(new ImageIcon("images/bg.png"));
		
		gamepanel = new GameJPanel(800,480);
		//����λ�úʹ�С
		gamepanel.setBounds(40,65,800,480);
		
		
		//����ͼƬһ�����label��
		label = new JLabel(new ImageIcon("images/bg.png"));
		
		//������С����ť
		min = new JButton(new ImageIcon("images/btn-2.png"));
		//�����رհ�ť
		close = new JButton(new ImageIcon("images/btn-4.png"));		
		min.setBounds(772, 28, 32, 32);
		//���ñ߿�
		min.setBorderPainted(false);
		//����ͼƬ�Ƿ����label
		min.setContentAreaFilled(false);
		
		//��min����¼�Դ
		min.addActionListener(this);
		//�����ڲ���
		min.addMouseListener( new MouseAdapter(){
			//������
			@Override
			public void mouseEntered(MouseEvent e) {
				min.setIcon(new ImageIcon("images/btn-1.png"));
			}
			//����Ƴ�
			@Override
			public void mouseExited(MouseEvent e) {
				min.setIcon(new ImageIcon("images/btn-2.png"));
			}
		});	
				
		//���ùرհ�ťλ�ô�С
		close.setBounds(810, 28, 32, 32);
		//���ñ߿�
		close.setBorderPainted(false);
		//����ͼƬ�Ƿ����label
		close.setContentAreaFilled(false);
		close.addActionListener(this);
		//�����ڲ���
		close.addMouseListener( new MouseAdapter(){
			//������
			@Override
			public void mouseEntered(MouseEvent e) {
				
				close.setIcon(new ImageIcon("images/btn-3.png"));
			}
			//����Ƴ�
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
		isDraging = true;//����Ѿ�������
		X = e.getX();
		Y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		isDraging = false;
	}

}
