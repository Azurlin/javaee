package student;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TchMGT extends JFrame{
	

    public TchMGT(){
    	
    	JLabel jl = new JLabel("欢迎使用学生信息管理系统");
		Font ft =new Font("宋体", 1, 24);
		jl.setFont(ft);
		jl.setBounds(90, 0, 300, 80);
    	JButton btn1 = new JButton("学生成绩管理");
    	JButton btn2 = new JButton("学生信息管理");
    	
    	
    	
    	btn1.setBounds(50, 200, 150, 40);
    	btn2.setBounds(280, 200, 150, 40);
    	
    	JPanel jp = new JPanel();
    	jp.setLayout(null);
    	
    	jp.add(jl);
    	jp.add(btn1);
    	jp.add(btn2);
    	add(jp);
    	setTitle("管理员");
    	setResizable(false); //设置窗口不可改变大小
        setBounds(700,300,500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
        
        
        
        btn1.addActionListener(new ActionListener(){//添加事件  点击“学生成绩管理”
        	public void actionPerformed(ActionEvent e){
	               ScoreMGT sc = new ScoreMGT();
	               setVisible(false);
 	       }
 	     });
        btn2.addActionListener(new ActionListener(){//添加事件  点击“学生信息管理”
     	    public void actionPerformed(ActionEvent e){
     	    	StudentsInf su = new StudentsInf(); 
     	    	setVisible(false);
     	       }
     	});
        
    }
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TchMGT th = new TchMGT();
	}



}
