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
	String[] inf = new String[]{"����","����","����ȷ��","���֤����","ѧ��","�Ա�","����","�ֻ���","Ժϵ"};
	JPanel panel;
	JTextField[] jt = new JTextField[9];
	String[] tips = new String[]{"��������Ч֤����д","������6-16λӢ����ĸ�����ֺ������ַ����","������дһ�����룬�Ը������������Ƿ���ȷ","������˶�����֤������","����������ѧ��","","������һ����ȫ���䣬�Ա������������ʱ���һ�����","������11λ�ֻ�����",""};
	String[] data = new String[9];//����ı������������
	JButton jbRegister,jbLogin;//ע�ᣬ���ذ�ť
	public Register(){
		panel = new JPanel();
		panel.setLayout(null);
		for(int i=0;i<9;i++){
			JLabel labell = new JLabel(inf[i]);
			labell.setFont(new Font("������", Font.BOLD, 20));
			labell.setBounds(50,150+75*i,120,50);
			labell.setAlignmentX(1);//�Ҷ���
			panel.add(labell);
			
			if(i==1||i==2)
				jt[i]=new JPasswordField();
			else
				jt[i]=new JTextField();
			jt[i].setFont(new Font("������", Font.PLAIN, 20));
			jt[i].setBounds(200,150+75*i,300,50);
			panel.add(jt[i]);
			
			JLabel labelr = new JLabel(tips[i]);
			labelr.setFont(new Font("������", Font.PLAIN, 20));
			labelr.setBounds(500,150+75*i,500,50);
			labelr.setAlignmentX(1);//�Ҷ���
			panel.add(labelr);
			
		}
		jbRegister = new JButton("ע��");
		jbRegister.setBounds(250,850,150,50);
		jbRegister.setFont(new Font("������", Font.PLAIN, 20));
		panel.add(jbRegister);
		
		jbLogin = new JButton("���ص�½");
		jbLogin.setBounds(500,850,150,50);
		jbLogin.setFont(new Font("������", Font.PLAIN, 20));
		panel.add(jbLogin);
		
		this.add(panel);
		this.setTitle("ע����Ϣ");
		this.setBounds(450, 50, 1000, 1000);
		this.setResizable(false);
		this.setVisible(true);
		
		for(int i = 0;i<9;i++)
			jt[i].addActionListener(this);
		jbRegister.addActionListener(this);
		jbLogin.addActionListener(this);
	}
	public boolean check(){//���������Ϣ�Ƿ�����
		
		
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
		for(int i=0;i<8;i++){//�Ѵ��ı������������д��������
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
