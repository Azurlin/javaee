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
//维护表格
public class ScoreMGT extends JFrame implements ActionListener{

    private DefaultTableModel tableModel;   //表格模型对象
    private JTable table;
    private JTextField iTextField;
    private JTextField aTextField;
    private JTextField bTextField;
    private TableColumn col;
    PreparedStatement stmt;
    JButton jbUpdate;
    String[][] data;
    ArrayList<UpdateData> arr;//更新与之前不同的数据
    String achv[][];//下拉表达对应的表格
    String tableName = "2019finalexam";
    public ScoreMGT()
    {
    	
        super();
        setTitle("成绩管理");
        setBounds(300,150,500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object [][]tableVales={ }; //数据
        String[] columnNames = {"编号","学号","姓名","语文","数学","英语"};   //列名
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        setSize(1200, 800);
      
        col =  table.getColumn("学号");
        col.setPreferredWidth(100);
        
       // table.setPreferredScrollableViewportSize(getSize());
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        table.addMouseListener(new MouseAdapter(){    //鼠标事件
            public void mouseClicked(MouseEvent e){
                int selectedRow = table.getSelectedRow(); //获得选中行索引
                Object oa = tableModel.getValueAt(selectedRow, 0);
                Object ob = tableModel.getValueAt(selectedRow, 1);
                Object oc = tableModel.getValueAt(selectedRow, 2);
                iTextField.setText(oa.toString());  //给文本框赋值
                aTextField.setText(ob.toString());  //给文本框赋值
                bTextField.setText(oc.toString());
            }
        });
        scrollPane.setViewportView(table);
        
        data =Database.selectAchieve(Database.DBCon(), tableName);// 默认表单是2019期末考试
        for(int j=1;j<data.length;j++){
        	tableModel.addRow(data[j]);
        }
        
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel,BorderLayout.NORTH);
        
        
        JComboBox jcb = new JComboBox();
        achv = Database.selectAchieve(Database.DBCon(), "achievements");
        Object[] tables =new Object[achv.length-1];
        for(int i =0;i<achv.length-1;i++){
        	tables[i] = achv[i+1][1];
        }
        
		JComboBox comboBox = new JComboBox();
		ComboBoxModel comboBoxModel = new DefaultComboBoxModel(tables);//创建下拉列表模型
		comboBox.setModel(comboBoxModel);//向列表中添加数据模型
		
		comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // 选择的下拉框选项
                	for(int i =0;i<achv.length;i++)
                		if(achv[i][1] == e.getItem()){
                			delAll();
                			tableName = achv[i][2];
                			data =Database.selectAchieve(Database.DBCon(), achv[i][2]);
                			
                	        for(int j=1;j<data.length;j++){
                	        	tableModel.addRow(data[j]);
                	        }
                		}
                }
            }
        });
		
		panel.add(comboBox);
		
		
		
        panel.add(new JLabel("编号: "));
        iTextField = new JTextField("1",10);
        panel.add(iTextField);
        panel.add(new JLabel("学号: "));
        aTextField = new JTextField("2",10);
        panel.add(aTextField);
        panel.add(new JLabel("姓名: "));
        bTextField = new JTextField("3",10);
        panel.add(bTextField);
        
        
        JButton jbse = new JButton("查询");
        jbse.addActionListener(new ActionListener(){//添加事件
            @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
            	MessageBox.sfinit(table,data,1,2);
            }
        });
        
        panel.add(jbse);

        JButton updateButton = new JButton("更新");   //修改按钮
        updateButton.addActionListener(new ActionListener(){//添加事件
            @SuppressWarnings("unchecked")
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
            	}/*
            	for(int i =0;i<row;i++){
    				for(int j =0;j<column;j++){
    					System.out.print(table[i][j]+"  ");
    				}
    				System.out.println();
            	}
            	for(int i =0;i<arr.size();i++){
            		//System.out.println(arr.get(i).getrow()+" "+arr.get(i).column+" "+arr.get(i).data );
            	}*/
            	if(arr.size() !=0){//如果有变化，弹出消息提示是否更新
            		MessageBox.msgTip(tableName,arr);
            	}
            }
        });
        panel.add(updateButton);
        
        JButton jbBack = new JButton("返回  ");   //修改按钮
        jbBack.addActionListener(new ActionListener(){//添加事件
			public void actionPerformed(ActionEvent e){
					setVisible(false);
					TchMGT th = new TchMGT();
            		dispose();
    	        }
            
        
        });
        panel.add(jbBack);
        
        this.setVisible(true);
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	ScoreMGT jTableDefaultTableModelTest = new ScoreMGT();
    	//ScoreMGT jTa = new ScoreMGT(1);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
 		
	}
	
	  //点击查询弹出框
   
   public void delAll(){//删除所有行
	   int row = tableModel.getRowCount();
	   for(int i=0;i<row;i++){
		   tableModel.removeRow(row-i-1);
	   }
	   
   }

}

