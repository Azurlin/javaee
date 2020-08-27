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
		JLabel jl =new JLabel("��ȷ���Ƿ�Ҫ�����ύ");
		jl.setFont(new Font("������", Font.PLAIN, 20));
		jp.add(jl);
		JButton jby = new JButton("ȷ��");
		jby.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Database.update(Database.DBCon(),tableName, arr);
				jf.setVisible(false);
				
			}
		});
		jp.add(jby);
		JButton jbn = new JButton("����");
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
	       JButton sbtn1 = new JButton("��ѯ");
	       JButton sbtn2 = new JButton("�˳�");
	       JLabel sl1 = new JLabel("ѧ��:");
	       JLabel sl2 = new JLabel("����:");
	       JLabel sl3 = new JLabel("������������ѧ�Ž��в�ѯ");
	       final JLabel sl4 = new JLabel("��ѯʧ��!δ�ҵ�����");
	       sl4.setForeground(Color.red);
	       sl4.setVisible(false);
	       //lable����
	       sl4.setBounds(170, 150, 180, 30);
	       sl3.setBounds(170, 0, 180, 30);
	       sl1.setBounds(125, 60, 80, 30);
	       sl2.setBounds(125, 100, 80, 30);
	       //����򲼾�
	       idtext.setBounds(175, 60, 150, 30);
	       nametext.setBounds(175, 100, 150, 30);
	       //��ť����
	       sbtn1.setBounds(150, 200, 80, 40);
	       sbtn2.setBounds(270, 200, 80, 40);
	       //-------------��ѯ�¼�����-----------------
	       sbtn1.addActionListener(new ActionListener(){//����¼�
	       public void actionPerformed(ActionEvent e){
	    	  
	    	  String id = idtext.getText();
	    	  String name = nametext.getText();
	    	  for(int row=1;row<=(table.getRowCount());row++){

	    		  if(id.equals( data[row][x])||name.equals( data[row][y])){ //�Ƚϱ���������ֵ�Ƿ���ͬ
	    	    	  row = row-1 ;
	    	    	  table.setRowSelectionInterval(row, row);
	    	    	  table.scrollRectToVisible(table.getCellRect(row, 0, true));//�����Ӵ�����
	    	    	  table.setSelectionBackground(Color.LIGHT_GRAY);//ѡ�������ñ���ɫ
	    	    	  sl4.setVisible(false);//���ҳɹ�������ʾ��Ϣ
	    	    	  break;
	    		  }
	    		 
	    		  sl4.setVisible(true);//����ʧ����ʾ
	    	  }		               
	       }
	       });
	       
	       sbtn2.addActionListener(new ActionListener(){//����¼�
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
