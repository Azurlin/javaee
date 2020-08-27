package student;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
  
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame implements ActionListener {
 String userName;
 String password;
 String captcha;
 public static String randomcaptcha;
  
 public JLabel logoLabel, userNameLabel, passwordLabel, captchaLabel;
 public JTextField userNameInput, captchaInput;
 public JPasswordField passwordInput;
 public JButton login, register,change;
 public Panel panel;
  
 public Login() {
  setTitle("登陆");
  setSize(400, 300);
  setLocationRelativeTo(null);
  init();
  setVisible(true);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setResizable(false);
 }
  
 public void init() {
  setLayout(null);
  //logoLabel= new JLabel();
  //logoLabel.setIcon(new ImageIcon("C:\\Users\\dell\\Desktop\\快捷方式\\java\\SIMS\\1.jpg"));
  logoLabel = new JLabel(new ImageIcon("1.jpg"));
  logoLabel.setBounds(125, 10, 150, 70);
  add(logoLabel);
  
  userNameLabel = new JLabel("用户名:");
  userNameLabel.setBounds(90, 90, 60, 40);
  add(userNameLabel);
  userNameInput = new JTextField();
  userNameInput.setBounds(150, 100, 150, 20);
  add(userNameInput);
  
  passwordLabel = new JLabel("密码:");
  passwordLabel.setBounds(90, 120, 60, 40);
  add(passwordLabel);
  passwordInput = new JPasswordField();
  passwordInput.setBounds(150, 130, 150, 20);
  add(passwordInput);
  
  captchaLabel = new JLabel("验证码:");
  captchaLabel.setBounds(90, 150, 60, 40);
  add(captchaLabel);
  captchaInput = new JTextField();
  captchaInput.setBounds(150, 160, 70, 20);
  add(captchaInput);
  
  panel = new PanelDemo();
  panel.setBounds(220, 160, 80, 20);
  add(panel);
    
    
  change = new JButton("换一换");
  change.setBounds(300, 160, 80, 20);
  change.setContentAreaFilled(false);
  change.setBorderPainted(false);
  add(change);
  
  
  
  login = new JButton("登陆", new ImageIcon("login.gif"));
  login.setBounds(70, 200, 120, 30);
  login.setMnemonic(KeyEvent.VK_L);
  add(login);
  register = new JButton("注册", new ImageIcon("exit.gif"));
  register.setBounds(210, 200, 120, 30);
  register.setMnemonic(KeyEvent.VK_X);
  add(register);
  
  userNameInput.addActionListener(this);
  passwordInput.addActionListener(this);
  captchaInput.addActionListener(this);
  
  login.addActionListener(this);
  register.addActionListener(this);
  change.addActionListener(this);
 }
  
 	public void actionPerformed(ActionEvent e) {
  
 		userName = userNameInput.getText();
 		password = new String(passwordInput.getPassword());
 		captcha = captchaInput.getText();
    
 		if (e.getSource() == change) {
 			panel.repaint();
 		}
 		if (e.getSource() == login) {
 			if (captcha.equals(randomcaptcha)) {
 				int result = Database.login(Database.DBCon(),userName, password);
 			
 				if (result == 1) {//判断用户
 					JOptionPane.showMessageDialog(this, "登陆成功");
 					StudentMGT s = new StudentMGT(userName);
 				}
 				else if(result == 2){//管理员登陆
 					JOptionPane.showMessageDialog(this, "管理员登陆成功"); 
 					TchMGT d = new TchMGT();
 					dispose();
 				} else {
 					JOptionPane.showMessageDialog(this, "用户名密码不正确\n请重新输入");
 					panel.repaint();
 				}
 			}
 			else {
 				JOptionPane.showMessageDialog(this, "验证码不正确");
 		}
 		}
 		if (e.getSource() == register) {
 			//System.exit(0);
 			Register rframe = new Register();
 			//this.setVisible(false);
 			dispose();
 		
 		}
 	}
 public static void main(String[] args) {
  new Login();
 }
}
class PanelDemo extends Panel {//画验证码
  
 public void paint(Graphics g) {
  int width = 80;
  int height = 20;
  g.setColor(Color.LIGHT_GRAY);
  g.fillRect(0, 0, width, height);
  g.setColor(Color.BLACK);
  g.drawRect(0, 0, width, height);
  Random rd = new Random();
  for (int i = 0; i < 100; i++) {
   int x = rd.nextInt(width) - 2;
   int y = rd.nextInt(height) - 2;
   g.setColor(Color.RED);
   g.drawOval(x, y, 2, 2);
  }
  g.setFont(new Font("TimesRoman", Font.BOLD, 20));
  g.setColor(Color.BLUE);
  char[] c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
  StringBuffer sb = new StringBuffer();
  for (int i = 0; i < 4; i++) {
   int index = rd.nextInt(c.length);
   sb.append(c[index] + " ");
  }
  g.drawString(sb.toString(), 0, 18);
  
  String str = sb.toString().replaceAll(" ", "");
  Login.randomcaptcha = str;
 }
}

