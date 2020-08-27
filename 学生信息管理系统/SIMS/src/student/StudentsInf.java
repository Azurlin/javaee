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

	private DefaultTableModel tableModel;   //表格模型对象
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
    ArrayList<UpdateData> arr;//更新与之前不同的数据
    String achv[][];//下拉表达对应的表格
    String tableName = "student";
    int selectedRow ;//鼠标点击所在的行
	public StudentsInf(){
		
		setTitle("学生基本信息");
        setBounds(300,150,500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object [][]tableVales={ }; //数据
        String[] columnNames = {"编号","姓名","身份证号","学号","性别","邮箱","电话","院系"};   //列名
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        setSize(1200, 800);
      
        col =  table.getColumn("学号");
        col.setPreferredWidth(100);
		
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        table.addMouseListener(new MouseAdapter(){    //鼠标事件
            public void mouseClicked(MouseEvent e){
                selectedRow = table.getSelectedRow(); //获得选中行索引
                System.out.print(selectedRow);
                /*Object oa = tableModel.getValueAt(selectedRow, 0);
                Object ob = tableModel.getValueAt(selectedRow, 1);
                Object oc = tableModel.getValueAt(selectedRow, 2);
                iTextField.setText(oa.toString());  //给文本框赋值
                aTextField.setText(ob.toString());  //给文本框赋值
                bTextField.setText(oc.toString());*/
            }
        });
        scrollPane.setViewportView(table);
        
        data =Database.selectAchieve(Database.DBCon(), tableName);// 默认表单是2019期末考试
        
        for(int j=1;j<data.length;j++){
        	tableModel.addRow(data[j]);
        }
		JPanel jp = new JPanel();
		getContentPane().add(jp,BorderLayout.SOUTH);
		
		JButton jbse = new JButton("      查询      ");
        jbse.addActionListener(new ActionListener(){//添加事件
            @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
            	MessageBox.sfinit(table,data,3,1);
            }
        });
        jp.add(jbse);
        
		JButton jbAdd = new JButton("增加一条数据");
		jbAdd.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
	            AddDate ad = new AddDate();
	            dispose();
	        }
	    });
		jp.add(jbAdd);
		
		JButton jbDel = new JButton("删除一条数据");
		jbDel.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
            	int selectedRow = table.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {
                    tableModel.removeRow(selectedRow);  //删除行
                    System.out.print(data[selectedRow+1][3]);
                    Database.delrow(Database.DBCon(),data[selectedRow+1][3]);
                }
            }
		});
		jp.add(jbDel);
		
		JButton updateButton = new JButton("     更新         ");   //修改按钮
        updateButton.addActionListener(new ActionListener(){//添加事件
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
            	if(arr.size() !=0){//如果有变化，弹出消息提示是否更新
            		MessageBox.msgTip(tableName,arr);
            	}
            }
        });
        jp.add(updateButton);
        
        JButton jbf5 = new JButton("    刷新      ");   //修改按钮
        jbf5.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
            	reflash();
            }
        
        });
        jp.add(jbf5);
        
        JButton jbacv = new JButton("    成绩单   ");   //修改按钮
        jbacv.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
            		ScoreMGT smgt =new ScoreMGT();
            		dispose();
    	        }
            
        
        });
        jp.add(jbacv);
        
        JButton jbBack = new JButton("    返回   ");   //修改按钮
        jbBack.addActionListener(new ActionListener(){//添加事件
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
	public void delAll(){//删除所有行
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
