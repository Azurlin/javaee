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
		panel = new JPanel(){//关键代码，就是重写了paint的一个方法 
	        protected void paintComponent(Graphics g) { 
	          super.paintComponent(g); 
	          ImageIcon img = new ImageIcon("1.jpg");  
	         /** 
	          * bg.PNG这个地方换成自己的图片 
	          * 此处使用的相对路径，bg.png跟该测试类在同一路径下 
	          * 不过建议使用相对路径避免使用绝对路径 
	          */
	          img.paintIcon(this, g, 0, 0); 
	        } 
	     };
	    panel.setOpaque(true);
		//panel.setLayout(null);
		panel.setBounds(100,100,1000,800);
		
		
		
		
		
		
		
		this.add(panel);
		this.setTitle("注册信息");
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
