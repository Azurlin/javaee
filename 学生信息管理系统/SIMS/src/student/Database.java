package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import com.mysql.jdbc.ResultSetMetaData;

public class Database {

	/**
	 * @param args
	 */
public static Connection DBCon(){
		
	    String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	    String DB_URL = "jdbc:mysql://localhost:3306/student?characterEncoding=utf8";
	    String USER = "root";
	    String PASS = "";
        Connection conn = null;
        Statement stmt = null;
	    // ע�� JDBC ����
        try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
        // ������
        System.out.println("�������ݿ�...");
        try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
	public void register(Connection conn,String []str){//��Ϣע��,��student���в�����Ϣ
		try {
			PreparedStatement psql;//���������Ϣ
			String sql = "insert into student(name,id_card,stu_id,sex,email,phone,faculty) values(?,?,?,?,?,?,?)";
			psql= conn.prepareStatement(sql);
			for(int i=1;i<8;i++){
					psql.setString(i,str[i]);
			}
			psql.executeUpdate();
			//�����û�������
			sql = "insert into user_password(stu_id,password) values(?,?)";
			psql = conn.prepareStatement(sql);
			psql.setString(1,str[3]);
			psql.setString(2, str[0]);
			psql.executeUpdate();
			System.out.print("ע��ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	
	}
	public static int login(Connection conn,String id,String password){//����
		try {
			Statement stmt = conn.createStatement();
			String sql_user = "select * from user_password where stu_id = '"+id +"'and password = '"+password+"';";
			String sql_root = "select * from root where root_id = '"+id +"'and password = '"+password+"';";
			ResultSet rs = stmt.executeQuery(sql_root);
			if(rs.next() == true){
				return 2;
			}
			rs = stmt.executeQuery(sql_user);
			if(rs.next() == true){
				return 1;
			}else{
				return 0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static void addStudent(Connection conn,String tableName,int sum){//���su'm���˵��ɼ�����
		String name ="��Ǯ��������֣��������������������������ʩ�ſײ��ϻ���κ�ս���л������ˮ��������˸��ɷ�����³Τ������ﻨ������Ԭ��ۺ��ʷ�Ʒ����Ѧ�׺����������ޱϺ�����������ʱ��Ƥ���뿵����Ԫ������ƽ�ƺ�������Ҧ��տ����ë����ױ���갼Ʒ��ɴ�̸��é���ܼ�������ף������������ϯ����ǿ��·¦Σ��ͯ�չ�÷ʢ�ֵ�����������Ĳ��﷮���������֧���ù�¬Ī�������Ѹɽ�Ӧ�ڶ����ڵ��������������ʯ�޼�ť�������ϻ���½��������춻�����ҷ����ഢ���������ɾ��θ����ڽ��͹�����ɽ�ȳ������ȫۭ�����������������ﱩ�����������������ղ����Ҷ��˾��۬�輻��ӡ�ް׻���ۢ�Ӷ����̼���׿�����ɳ����������ܲ�˫��ݷ����̷�����̼������";
		Random r = new Random();
		DecimalFormat df = new DecimalFormat("0000000");
		try {
			PreparedStatement psql;
			String sql = "insert into "+tableName+"(stu_id,name) values(?,?)";
			for(int i=0;i<sum;i++){
				psql= conn.prepareStatement(sql);
				psql.setString(1, 2017+""+df.format(r.nextInt(1000000)));
				psql.setString(2,""+name.charAt(r.nextInt(name.length()))+name.charAt(r.nextInt(name.length()))+name.charAt(r.nextInt(name.length())));
				psql.executeUpdate();
			}
			System.out.print("����ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String[][] selectAchieve(Connection conn,String tableName){//��ѯ�������
		try {//�� �� ���ﷵ�ص������Ǵ���ͷ�� �����ݶ�һ��
			String sql = "select count(*) from information_schema.COLUMNS where table_name='"+tableName+"'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int column = rs.getInt(1);//����
			
			
			sql="select count(*)  from "+tableName;
		    rs=stmt.executeQuery(sql);
		    rs.next();
		    int row=rs.getInt(1);//����
		    String [][] datas = new String[row+1][column];
		    
		    sql = "select * from "+tableName;
			PreparedStatement cs = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			ResultSetMetaData data = (ResultSetMetaData) rs.getMetaData();
			int first =1;
			for(int i=1;rs.next();i++){
				if(i ==1){//��ӱ�ͷ
					for(int j = 1 ; j <= data.getColumnCount() ; j++){ 
						System.out.println(data.getColumnName(j));
						datas[0][j-1] = data.getColumnName(j); 
					}
					first = 0;
				}
				for(int j=0;j < column;j++){
					//if(j == 0)
						//datas[i][j] = rs.getString(datas[0][j]);
					datas[i][j] = rs.getString(datas[0][j]);
				}
			}
			/*for(int i =0;i<row+1;i++){
				for(int j =0;j<column;j++){
					System.out.print(datas[i][j]+"  ");
				}
				System.out.println();
			}*/
			return datas;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static void update(Connection conn,String tableName,ArrayList<UpdateData> arr){//���º�֮ǰ��ͬ������
		System.out.println(tableName);
		for(int i =0;i<arr.size();i++){
    		System.out.println(arr.get(i).getrow()+" "+arr.get(i).column+" "+arr.get(i).data+" "+arr.get(i).row_id+" "+arr.get(i).column_name );
    	}
		try {
			PreparedStatement psql;
			for(int i=0;i<arr.size();i++){
				String sql = "update "+tableName+" set "+arr.get(i).column_name +" = '"+arr.get(i).data+"' where id = "+arr.get(i).row_id+";";
				psql= conn.prepareStatement(sql);
				psql.executeUpdate();
				
			}
			System.out.print("���³ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	@SuppressWarnings("static-access")
	
	public static void adduser(Connection conn,int sum){//���su'm���˵��ɼ�����
		String name ="��Ǯ��������֣��������������������������ʩ�ſײ��ϻ���κ�ս���л������ˮ��������˸��ɷ�����³Τ������ﻨ������Ԭ��ۺ��ʷ�Ʒ����Ѧ�׺����������ޱϺ�����������ʱ��Ƥ���뿵����Ԫ������ƽ�ƺ�������Ҧ��տ����ë����ױ���갼Ʒ��ɴ�̸��é���ܼ�������ף������������ϯ����ǿ��·¦Σ��ͯ�չ�÷ʢ�ֵ�����������Ĳ��﷮���������֧���ù�¬Ī�������Ѹɽ�Ӧ�ڶ����ڵ��������������ʯ�޼�ť�������ϻ���½��������춻�����ҷ����ഢ���������ɾ��θ����ڽ��͹�����ɽ�ȳ������ȫۭ�����������������ﱩ�����������������ղ����Ҷ��˾��۬�輻��ӡ�ް׻���ۢ�Ӷ����̼���׿�����ɳ����������ܲ�˫��ݷ����̷�����̼������";
		String []fname = new String[]{"��Ϣ","��ѧ","�����","����","ͳ��","���","��ľ","��ѧ","�����","��е","��ý","����","��"};
		Random r = new Random();
		DecimalFormat df = new DecimalFormat("0000000");
		String[] stu_id =new String[sum];
		String[] names =new String[sum];
		try {
			PreparedStatement psql;
			String sql = "insert into  student(name,id_card,stu_id,sex,email,phone,faculty) values(?,?,?,?,?,?,?)";
			for(int i=0;i<sum;i++){
				psql= conn.prepareStatement(sql);
				String tmpn = ""+name.charAt(r.nextInt(name.length()))+name.charAt(r.nextInt(name.length()))+name.charAt(r.nextInt(name.length()));
				psql.setString(1,tmpn);
				names[i] = tmpn;
				psql.setString(2,rstring(16)+"");
				String tmp = 2017+""+df.format(r.nextInt(1000000));
				stu_id[i] = tmp;
				psql.setString(3,tmp );
				if(r.nextInt(2)==0)
					psql.setString(4, "��");
				else
					psql.setString(4, "Ů");
				
				psql.setString(5, rstring(11)+"@qq.com");
				psql.setString(6, "156"+rstring(8));
				psql.setString(7, fname[r.nextInt(13)]+"ѧԺ");
				psql.executeUpdate();
			}
			//�����û�������
			sql = "insert into user_password(stu_id,password) values(?,?)";
			for(int i= 0;i<sum;i++){
				psql = conn.prepareStatement(sql);
				psql.setString(1,stu_id[i]);
				psql.setString(2, "123456");
				psql.executeUpdate();
			}
			sql = "insert into 2019finalexam(stu_id,name,Chinese,Math,English) values(?,?,?,?,?)";
			for(int i= 0;i<sum;i++){
				psql = conn.prepareStatement(sql);
				psql.setString(1,stu_id[i]);
				psql.setString(2, names[i]);
				psql.setString(3, rstring(2));
				psql.setString(4, rstring(2));
				psql.setString(5, rstring(2));
				psql.executeUpdate();
			}
			sql = "insert into 2019midsemester(stu_id,name,Chinese,Math,English) values(?,?,?,?,?)";
			for(int i= 0;i<sum;i++){
				psql = conn.prepareStatement(sql);
				psql.setString(1,stu_id[i]);
				psql.setString(2, names[i]);
				psql.setString(3, rstring(2));
				psql.setString(4, rstring(2));
				psql.setString(5, rstring(2));
				psql.executeUpdate();
			}
			System.out.print("����ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String rstring(int sum){
		String tmp ="";
		Random r = new Random();
		for(int j=0;j<sum;j++){
			tmp += r.nextInt(10);
		}
		return tmp;
		
	}
	public static void delrow(Connection conn,String stu_id){
		
		String [] table = new String[]{"2019finalexam","2019midsemester","student","user_password"};
		PreparedStatement psql;
		try {
			for(int i=0;i<4;i++){
				String sql = "delete from "+table[i] +" where stu_id ='"+stu_id+"'; ";
				psql = conn.prepareStatement(sql);
				psql.executeUpdate();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	
	}
	public static String[] selStu(Connection conn,String stu_id){
		String sql = "select * from student where stu_id = '"+stu_id+"'";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String[] str =new String[]{"name","id_card","stu_id","sex","email","phone","faculty"};
			String[] data = new String[7];
			for(int i =0;i<7;i++){
				data[i] = rs.getString(str[i]);
			}
			return data;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static String[] selScroe(Connection conn,String tableName,String stu_id){
		String sql = "select * from "+tableName+" where stu_id = '"+stu_id+"'";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String[] str =new String[]{"Chinese","Math","English"};
			String[] data = new String[3];
			for(int i =0;i<3;i++){
				data[i] = rs.getString(str[i]);
			}
			return data;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Database dt = new Database();
		//String [] str =new String[]{"1","2","3","4","5","6","7","8","9","10"};
		//dt.register(dt.DBCon(),str);
		//String name ="��Ǯ��������֣��������������������������ʩ�ſײ��ϻ���κ�ս���л������ˮ��������˸��ɷ�����³Τ������ﻨ������Ԭ��ۺ��ʷ�Ʒ����Ѧ�׺����������ޱϺ�����������ʱ��Ƥ���뿵����Ԫ������ƽ�ƺ�������Ҧ��տ����ë����ױ���갼Ʒ��ɴ�̸��é���ܼ�������ף������������ϯ����ǿ��·¦Σ��ͯ�չ�÷ʢ�ֵ�����������Ĳ��﷮���������֧���ù�¬Ī�������Ѹɽ�Ӧ�ڶ����ڵ��������������ʯ�޼�ť�������ϻ���½��������춻�����ҷ����ഢ���������ɾ��θ����ڽ��͹�����ɽ�ȳ������ȫۭ�����������������ﱩ�����������������ղ����Ҷ��˾��۬�輻��ӡ�ް׻���ۢ�Ӷ����̼���׿�����ɳ����������ܲ�˫��ݷ����̷�����̼������";
		Random r = new Random();
		/*
		DecimalFormat df = new DecimalFormat("0000000");
		System.out.println(2017+""+df.format(r.nextInt(1000000)));//��ʽ���ַ��������Ĳ���
		System.out.println(""+name.charAt(r.nextInt(name.length()))+name.charAt(r.nextInt(name.length()))+name.charAt(r.nextInt(name.length())));//���ȡ������
		*/
		Database.adduser(Database.DBCon(),50);
		//String []str = Database.selStu(Database.DBCon(),"2");
	}

}
