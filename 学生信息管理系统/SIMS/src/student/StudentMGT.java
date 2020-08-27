package student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



public class StudentMGT extends JFrame{
	Image im =  null;
    //DBUti con = new DBUti();
    PreparedStatement stmt;
    String [][]tableVales={}; //����
    String achv[][];//��������Ӧ�ı��
    String tableName = "2019finalexam";
    private TableColumn col;
    private DefaultTableModel tableModel;   //���ģ�Ͷ���
    private JTable table;
    String[][] data;
    JLabel  d ;
	JLabel  ee ;
	JLabel  f ;
    public StudentMGT( final String user)
    {
    	
        super();
        setTitle("ѧ����Ϣ");
        
        try {
			im = ImageIO.read(new File(".\\im01.png")); //����ͼƬ
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String []str = Database.selStu(Database.DBCon(),user);
		JLabel email = new JLabel("��������:"+str[4]);
		JLabel name = new JLabel("����:"+str[0]);
		JLabel id_card = new JLabel("���֤��:"+str[1]);
		JLabel sex = new JLabel("�Ա�:"+str[3]);
		JLabel stu_id = new JLabel("ѧ��:"+str[2]);
		JLabel phone = new JLabel("��ϵ��ʽ:"+str[5]);
		JLabel school = new JLabel("Ժϵ:"+str[6]);
		JLabel score = new JLabel("��ѯ�ɼ�:");
		
		email.setSize(500,50);
		id_card.setSize(500,50);
		phone.setSize(500,50);
		
		Font ft =new Font("����", 1, 24);
		stu_id.setFont(ft);
		name.setFont(ft);
		id_card.setFont(ft);
		sex.setFont(ft);
		email.setFont(ft);
		phone.setFont(ft);
		school.setFont(ft);
		score.setFont(ft);
		
		
		setLayout(null);
		JPanel p1 = new JPanel();
		p1.setLocation(100, 100);
		p1.setLayout(new GridLayout(4, 2));
		p1.setBounds(200, 10, 700, 500);
		
		Object [][]tableVales={ }; //����
        String[] columnNames = {"���","ѧ��","����","����","��ѧ","Ӣ��"};   //����
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        setSize(1200, 800);
      
        col =  table.getColumn("ѧ��");
        col.setPreferredWidth(100);
        
		
        achv = Database.selectAchieve(Database.DBCon(), "achievements");
        Object[] tables =new Object[achv.length-1];
        for(int i =0;i<achv.length-1;i++){
        	tables[i] = achv[i+1][1];
        }
		JComboBox comboBox = new JComboBox();
		ComboBoxModel comboBoxModel = new DefaultComboBoxModel(tables);//���������б�ģ��
		comboBox.setModel(comboBoxModel);//���б����������ģ��
		
		comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // ѡ���������ѡ��
                	for(int i =0;i<achv.length;i++)
                		if(achv[i][1] == e.getItem()){
                			tableName = achv[i][2];
                			String[]str = Database.selScore(Database.DBCon().achv[i][2],"");
                			d.setText(str[0]);
                			ee.setText(str[1]);.columnNames.
                			
                			f.setText(str[2]);
                		}
                }
            }
        });
		
		
		
		p1.add(name);
		p1.add(email);
		p1.add(sex);
		p1.add(id_card);
		p1.add(stu_id);
		p1.add(phone);
		p1.add(school);
		p1.add(score);
		JPanel jp = new JPanel();
		jp.setBounds(670,430,150,400);
		jp.add(comboBox);
		add(jp);
		add(p1);
		
		JPanel jpl = new JPanel();
		jpl.setBounds(200,500,600,200);
		jpl.setLayout(new GridLayout(2, 3));
		
		JLabel  a = new JLabel("����");
		JLabel  b = new JLabel("��ѧ");
		JLabel  c = new JLabel("Ӣ��");
		d = new JLabel("");
		ee = new JLabel("");
		f = new JLabel("");
		
		Font ft1 =new Font("����", 1, 24);
		a.setFont(ft1);
		b.setFont(ft1);
		c.setFont(ft1);
		d.setFont(ft1);
		ee.setFont(ft1);
		f.setFont(ft1);
		
		jpl.add(a);
		jpl.add(b);
		jpl.add(c);
		jpl.add(d);
		jpl.add(ee);
		jpl.add(f);
		jpl.setBackground(new java.awt.Color(255,160,122));
		add(jpl);
		
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 1000, 800);
        setVisible(true); 
    }
    public void delAll(){//ɾ��������
 	   int row = tableModel.getRowCount();
 	   for(int i=0;i<row;i++){
 		   tableModel.removeRow(row-i-1);
 	   }
 	   
    }
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(im, 20, 50, null);		
		//repaint();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentMGT i = new StudentMGT("20170840378");
	}



}

