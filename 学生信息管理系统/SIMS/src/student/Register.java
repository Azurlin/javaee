package student;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Register extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	String[] inf = new String[]{"姓名","密码","密码确认","身份证号码","学号","性别","邮箱","手机号","院系"};
	JPanel panel;
	JTextField[] jt = new JTextField[9];
	String[] tips = new String[]{"按本人有效证件填写","密码由6-16位英文字母、数字和特殊字符组成","请在填写一边密码，以辅助检验密码是否正确","请认真核对您的证件号码","请输入您的学号","","请设置一个安全邮箱，以便在忘记密码的时候找回密码","请输入11位手机号码",""};
	String[] data = new String[9];//存从文本框输入的内容
	JButton jbRegister,jbLogin;//注册，返回按钮
	public Register(){
		panel = new JPanel();
		panel.setLayout(null);
		for(int i=0;i<9;i++){
			JLabel labell = new JLabel(inf[i]);
			labell.setFont(new Font("新宋体", Font.BOLD, 20));
			labell.setBounds(50,150+75*i,120,50);
			labell.setAlignmentX(1);//右对齐
			panel.add(labell);
			
			if(i==1||i==2)
				jt[i]=new JPasswordField();
			else
				jt[i]=new JTextField();
			jt[i].setFont(new Font("新宋体", Font.PLAIN, 20));
			jt[i].setBounds(200,150+75*i,300,50);
			panel.add(jt[i]);
			
			JLabel labelr = new JLabel(tips[i]);
			labelr.setFont(new Font("新宋体", Font.PLAIN, 20));
			labelr.setBounds(500,150+75*i,500,50);
			labelr.setAlignmentX(1);//右对齐
			panel.add(labelr);
			
		}
		jbRegister = new JButton("注册");
		jbRegister.setBounds(250,850,150,50);
		jbRegister.setFont(new Font("新宋体", Font.PLAIN, 20));
		panel.add(jbRegister);
		
		jbLogin = new JButton("返回登陆");
		jbLogin.setBounds(500,850,150,50);
		jbLogin.setFont(new Font("新宋体", Font.PLAIN, 20));
		panel.add(jbLogin);
		
		this.add(panel);
		this.setTitle("注册信息");
		this.setBounds(450, 50, 1000, 1000);
		this.setResizable(false);
		this.setVisible(true);
		
		for(int i = 0;i<9;i++)
			jt[i].addActionListener(this);
		jbRegister.addActionListener(this);
		jbLogin.addActionListener(this);
	}
	public boolean check(){//检查输入信息是否有误
		
		
		return true;
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Register r = new Register();
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {//
		// TODO Auto-generated method stub
		for(int i=0;i<8;i++){//把从文本框输入的内容写入数组中
			if(i==0)
				data[0] = new String(((JPasswordField) jt[1]).getPassword());
			else if(i==1){
				data[1] = jt[0].getText();
			}
			else
				data[i] = jt[i+1].getText();
		}
		
		if(e.getSource() == jbRegister){
			for(int i=0;i<9;i++){
				Syst
			}
		}
			
		if(e.getSource() == jbLogin){
			Login login = new Login();
			dispose();
		}
	}


}
