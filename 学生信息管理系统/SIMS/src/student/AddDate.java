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
public class AddDate extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	String[] inf = new String[]{"����","���֤����","ѧ��","�Ա�","����","�ֻ���","Ժϵ"};
	JPanel panel;
	JTextField[] jt = new JTextField[7];
	//String[] tips = new String[]{"��������Ч֤����д","������6-16λӢ����ĸ�����ֺ������ַ����","������дһ�����룬�Ը������������Ƿ���ȷ","����������ѧ��","","������˶�����֤������","������һ����ȫ���䣬�Ա������������ʱ���һ�����","������11λ�ֻ�����",""};
	String[] data = new String[8];//����ı������������
	JButton jbRegister,jbLogin;//ע�ᣬ���ذ�ť
	public AddDate(){
		panel = new JPanel();
		panel.setLayout(null);
		for(int i=0;i<7;i++){
			JLabel labell = new JLabel(inf[i]);
			labell.setFont(new Font("������", Font.BOLD, 20));
			labell.setBounds(200,150+75*i,120,50);
			labell.setAlignmentX(1);//�Ҷ���
			panel.add(labell);
			jt[i]=new JTextField();
			jt[i].setFont(new Font("������", Font.PLAIN, 20));
			jt[i].setBounds(400,150+75*i,300,50);
			panel.add(jt[i]);
			
		}
		jbRegister = new JButton("���");
		jbRegister.setBounds(250,850,150,50);
		jbRegister.setFont(new Font("������", Font.PLAIN, 20));
		panel.add(jbRegister);
		
		jbLogin=new JButton("����");
		jbLogin.setBounds(500,850,150,50);
		jbLogin.setFront
		
		this.add(panel);
		this.setTitle("ע����Ϣ");
		this.setBounds(450, 50, 1000, 1000);
		this.setResizable(false);
		this.setVisible(true);
		
		for(int i = 0;i<7;i++)
			jt[i].addActionListener(this);
		jbRegister.addActionListener(this);
		jbLogin.addActionListener(this);
	}
	public boolean check(){//���������Ϣ�Ƿ�����
		
		
		return true;
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddDate r = new AddDate();
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {//
		// TODO Auto-generated method stub
		data[0] = "123456";//Ĭ��������123456
		for(int i=1;i<7;i++){//�Ѵ��ı������������д��������
				data[i] = jt[i].getText();
		}
		
		if(e.getSource() == jbRegister){
			for(int i =0;i<7;i++){
				System.out.println(data[i]);
			}
			if(check()){//�����д���ݷ��Ϲ淶
				Database dt = new Database();
				dt.register(dt.DBCon(),data);
			}
		}
		if(e.getSource() == jbLogin){
			StudentsInf is = new StudentsInf();
			dispose();
		}
	}


}
