package student;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MessageBox {
	
	
	public static void msgTip(final String tableName,final ArrayList<UpdateData> arr){
		
    	final JFrame jf = new JFrame();
		JPanel jp = new JPanel();
		JLabel jl =new JLabel("请确认是否要更新提交");
		jl.setFont(new Font("新宋体", Font.PLAIN, 20));
		jp.add(jl);
		JButton jby = new JButton("确认");
		jby.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Database.update(Database.DBCon(),tableName, arr);
				jf.setVisible(false);
				
			}
		});
		jp.add(jby);
		JButton jbn = new JButton("返回");
		jbn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jf.setVisible(false);
				
			}
		});
		jp.add(jbn);
		//Database.update(Database.DBCon(),"2019finalexam", arr);
		
		jf.add(jp);
		jf.setBounds(800, 450, 250, 125);
		jf.setVisible(true);
    	
    }
	
	public static void sfinit(final JTable table,final String[][] data,final int x,final int y) {
	   	//*************************
	       final JFrame sf = new JFrame();
	       JPanel sp = new JPanel();
	       
	       sp.setLayout(null);
	       final JTextField idtext = new JTextField();
	       final JTextField nametext = new JTextField();
	       JButton sbtn1 = new JButton("查询");
	       JButton sbtn2 = new JButton("退出");
	       JLabel sl1 = new JLabel("学号:");
	       JLabel sl2 = new JLabel("姓名:");
	       JLabel sl3 = new JLabel("请输入姓名或学号进行查询");
	       final JLabel sl4 = new JLabel("查询失败!未找到对象");
	       sl4.setForeground(Color.red);
	       sl4.setVisible(false);
	       //lable布局
	       sl4.setBounds(170, 150, 180, 30);
	       sl3.setBounds(170, 0, 180, 30);
	       sl1.setBounds(125, 60, 80, 30);
	       sl2.setBounds(125, 100, 80, 30);
	       //输入框布局
	       idtext.setBounds(175, 60, 150, 30);
	       nametext.setBounds(175, 100, 150, 30);
	       //按钮布局
	       sbtn1.setBounds(150, 200, 80, 40);
	       sbtn2.setBounds(270, 200, 80, 40);
	       //-------------查询事件监听-----------------
	       sbtn1.addActionListener(new ActionListener(){//添加事件
	       public void actionPerformed(ActionEvent e){
	    	  
	    	  String id = idtext.getText();
	    	  String name = nametext.getText();
	    	  for(int row=1;row<=(table.getRowCount());row++){

	    		  if(id.equals( data[row][x])||name.equals( data[row][y])){ //比较表格与输入的值是否相同
	    	    	  row = row-1 ;
	    	    	  table.setRowSelectionInterval(row, row);
	    	    	  table.scrollRectToVisible(table.getCellRect(row, 0, true));//超出视窗滚动
	    	    	  table.setSelectionBackground(Color.LIGHT_GRAY);//选中行设置背景色
	    	    	  sl4.setVisible(false);//查找成功隐藏提示信息
	    	    	  break;
	    		  }
	    		 
	    		  sl4.setVisible(true);//查找失败提示
	    	  }		               
	       }
	       });
	       
	       sbtn2.addActionListener(new ActionListener(){//添加事件
		       public void actionPerformed(ActionEvent e){	    	  
		    	   	sf.setVisible(false);
		       }
		       });
	       
	       //------------------------------------------
	       
	       sp.add(sl4);
	       sp.add(sl3);
	       sp.add(sl1);
	       sp.add(sl2);
	       sp.add(sbtn1);
	       sp.add(sbtn2);
	       sp.add(idtext);
	       sp.add(nametext);
	       sf.add(sp);
	       sf.setResizable(false);
	       sf.setLocation(500, 200);
	       sf.setSize(500, 300);
	       sf.setVisible(true);
	     
	   }
}
