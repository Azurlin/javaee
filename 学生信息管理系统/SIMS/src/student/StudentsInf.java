package student;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class StudentsInf extends JFrame{

	private DefaultTableModel tableModel;   //���ģ�Ͷ���
    private JTable table;
    private JTextField iTextField;
    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField cTextField;
    private JTextField dTextField;
    private JTextField eTextField;
    private TableColumn col;
    PreparedStatement stmt;
    JButton jbUpdate;
    String[][] data;
    ArrayList<UpdateData> arr;//������֮ǰ��ͬ������
    String achv[][];//��������Ӧ�ı��
    String tableName = "student";
    int selectedRow ;//��������ڵ���
	public StudentsInf(){
		
		setTitle("ѧ��������Ϣ");
        setBounds(300,150,500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object [][]tableVales={ }; //����
        String[] columnNames = {"���","����","���֤��","ѧ��","�Ա�","����","�绰","Ժϵ"};   //����
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        setSize(1200, 800);
      
        col =  table.getColumn("ѧ��");
        col.setPreferredWidth(100);
		
        JScrollPane scrollPane = new JScrollPane(table);   //֧�ֹ���
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //��ѡ
        table.addMouseListener(new MouseAdapter(){    //����¼�
            public void mouseClicked(MouseEvent e){
                selectedRow = table.getSelectedRow(); //���ѡ��������
                System.out.print(selectedRow);
                /*Object oa = tableModel.getValueAt(selectedRow, 0);
                Object ob = tableModel.getValueAt(selectedRow, 1);
                Object oc = tableModel.getValueAt(selectedRow, 2);
                iTextField.setText(oa.toString());  //���ı���ֵ
                aTextField.setText(ob.toString());  //���ı���ֵ
                bTextField.setText(oc.toString());*/
            }
        });
        scrollPane.setViewportView(table);
        
        data =Database.selectAchieve(Database.DBCon(), tableName);// Ĭ�ϱ���2019��ĩ����
        
        for(int j=1;j<data.length;j++){
        	tableModel.addRow(data[j]);
        }
		JPanel jp = new JPanel();
		getContentPane().add(jp,BorderLayout.SOUTH);
		
		JButton jbse = new JButton("      ��ѯ      ");
        jbse.addActionListener(new ActionListener(){//����¼�
            @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
            	MessageBox.sfinit(table,data,3,1);
            }
        });
        jp.add(jbse);
        
		JButton jbAdd = new JButton("����һ������");
		jbAdd.addActionListener(new ActionListener(){//����¼�
			public void actionPerformed(ActionEvent e){
	            AddDate ad = new AddDate();
	            dispose();
	        }
	    });
		jp.add(jbAdd);
		
		JButton jbDel = new JButton("ɾ��һ������");
		jbDel.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){
            	int selectedRow = table.getSelectedRow();//���ѡ���е�����
                if(selectedRow!=-1)  //����ѡ����
                {
                    tableModel.removeRow(selectedRow);  //ɾ����
                    System.out.print(data[selectedRow+1][3]);
                    Database.delrow(Database.DBCon(),data[selectedRow+1][3]);
                }
            }
		});
		jp.add(jbDel);
		
		JButton updateButton = new JButton("     ����         ");   //�޸İ�ť
        updateButton.addActionListener(new ActionListener(){//����¼�
            public void actionPerformed(ActionEvent e){
            	int row =tableModel.getRowCount();
            	int column = tableModel.getColumnCount();
            	Object[][] table = new String[row][column];
            	arr = new ArrayList<UpdateData>();
            	
            	for(int i =0;i<row;i++){
    				for(int j =0;j<column;j++){
    					table[i][j] = tableModel.getValueAt(i,j);
    					if(data[i+1][j] != tableModel.getValueAt(i,j)){
    						System.out.println(data[i][j]+"||"+tableModel.getValueAt(i,j));
    						arr.add(new UpdateData(i,j,Integer.parseInt(data[i+1][0]),data[0][j],(String) tableModel.getValueAt(i,j)));
    						data[i+1][j] = (String)tableModel.getValueAt(i,j);
    					}
    				}
            	}
            	if(arr.size() !=0){//����б仯��������Ϣ��ʾ�Ƿ����
            		MessageBox.msgTip(tableName,arr);
            	}
            }
        });
        jp.add(updateButton);
        
        JButton jbf5 = new JButton("    ˢ��      ");   //�޸İ�ť
        jbf5.addActionListener(new ActionListener(){//����¼�
			public void actionPerformed(ActionEvent e){
            	reflash();
            }
        
        });
        jp.add(jbf5);
        
        JButton jbacv = new JButton("    �ɼ���   ");   //�޸İ�ť
        jbacv.addActionListener(new ActionListener(){//����¼�
			public void actionPerformed(ActionEvent e){
            		ScoreMGT smgt =new ScoreMGT();
            		dispose();
    	        }
            
        
        });
        jp.add(jbacv);
        
        JButton jbBack = new JButton("    ����   ");   //�޸İ�ť
        jbBack.addActionListener(new ActionListener(){//����¼�
			public void actionPerformed(ActionEvent e){
					setVisible(false);
					TchMGT th = new TchMGT();
            		dispose();
    	        }
            
        
        });
        jp.add(jbBack);
        
        this.setVisible(true);
        
        
	}
	public void reflash(){
		delAll();
		data =Database.selectAchieve(Database.DBCon(), tableName);
        for(int j=1;j<data.length;j++){
        	tableModel.addRow(data[j]);
        }
		
	}
	public void delAll(){//ɾ��������
		   int row = tableModel.getRowCount();
		   for(int i=0;i<row;i++){
			   tableModel.removeRow(row-i-1);
		   }
		   
	   }
	
	
	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentsInf si = new StudentsInf();
	}

}
