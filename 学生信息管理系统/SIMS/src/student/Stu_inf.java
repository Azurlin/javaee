package student;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Stu_inf extends JFrame implements ActionListener,MouseListener{

	/**
	 * @param args
	 */
	JPanel panel,panel_acv;
	JButton jb,jbLogin_acv;
	public Stu_inf(){
		panel = new JPanel(){//�ؼ����룬������д��paint��һ������ 
	        protected void paintComponent(Graphics g) { 
	          super.paintComponent(g); 
	          ImageIcon img = new ImageIcon("1.jpg");  
	         /** 
	          * bg.PNG����ط������Լ���ͼƬ 
	          * �˴�ʹ�õ����·����bg.png���ò�������ͬһ·���� 
	          * ��������ʹ�����·������ʹ�þ���·�� 
	          */
	          img.paintIcon(this, g, 0, 0); 
	        } 
	     };
	    panel.setOpaque(true);
		//panel.setLayout(null);
		panel.setBounds(100,100,1000,800);
		
		
		
		
		
		
		
		this.add(panel);
		this.setTitle("ע����Ϣ");
		this.setBounds(450, 50, 1000, 1000);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stu_inf f = new Stu_inf();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
