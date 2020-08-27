package gobang;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//五子棋
public class gobang {
	ppaint p1 = new ppaint();

	public class ppaint extends JPanel implements MouseListener {
		private Graphics g;
		private int fallcount = 1; // 控制落子顺序
		private int[][] go = new int[16][16];

		public void paint(Graphics g) {
			int c = 50;
			// 绘制棋盘
			g.setColor(Color.BLACK);
			for (int i = 0; i < 16; i++) {
				g.drawLine(50, c, 800, c);// 画横线
				g.drawLine(c, 50, c, 800);// 画竖线
				c = 50 + c;
			}
		}// paint

		// 鼠标事件监听
		public void mouseClicked(MouseEvent e) {
			double x, y, ix, dx, iy, dy;
			if (e.getButton() == e.BUTTON1) {// 判断获取的按钮是否为鼠标的左击
				x = e.getX();
				y = e.getY(); // 获取鼠标点击xy坐标
				if (x >= 50 && x <= 800 && y >= 50 && y <= 800) {// 判断鼠标点击是否越界
					// System.out.println("点击坐标："+"X:"+x+"Y:"+y);
					Graphics g = getGraphics(); // 获取绘图组件

					// 修正坐标
					ix = Math.floor(x / 50); // 向下取整获取整数部分
					iy = Math.floor(y / 50);// *
					dx = (x / 50 - ix); // 获取小数部分
					dy = (y / 50 - iy);// *
					if (dx >= 0.5) {
						x = ix * 50 + 50;
					} else {
						x = ix * 50;
					}
					if (dy >= 0.5) {
						y = iy * 50 + 50;
					} else {
						y = iy * 50;
					}
					// 判断重复 绘制棋子并判断胜负
					if (go[(int) x / 50 - 1][(int) y / 50 - 1] == 0) { // 判断是否重复落子
						// 将棋子信息存入数组
						// 绘制棋子
						fallcount++;
						if (fallcount % 2 == 0) {// 设置颜色
							g.setColor(Color.BLACK);
							go[(int) x / 50 - 1][(int) y / 50 - 1] = 1;
						} else {
							g.setColor(Color.WHITE);
							go[(int) x / 50 - 1][(int) y / 50 - 1] = 2;
						}
						g.fillOval((int) x - 25, (int) y - 25, 45, 45);
						// 判断胜负
						if (judge((int) x / 50 - 1, (int) y / 50 - 1)) {
							if (fallcount % 2 == 0) {
								System.out.println("黑方胜利");
								replay(1);
							} else {
								System.out.println("白方胜利");
								replay(0);
							}
						}
					} else {
						System.out.println("该位置已有棋子请重下：\n");
					}
					//System.out.println("修正后坐标：" + "X:" + x + "Y:" + y);
				}// if(x>=50&&x<=850&&y>=50&&y<=850)
			}// if(e.getButton()==e.BUTTON1)
		}// mouseClicked(MouseEvent e)

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		// 判断胜负函数
		public boolean judge(int X, int Y) {
			boolean flag = false;
			int fa = go[X][Y];
			int count = 0;
			// 横向
			for (int t = 0; go[X + t][Y] == fa; t++) {
				count++;
				if ((X + t + 1) > 15)
					break;
			}
			for (int t = 0; go[X - t][Y] == fa; t++) {
				count++;
				if ((X - t - 1) < 0)
					break;
			}
			if (count >= 6) {
				flag = true;
			}
			// 纵向
			count = 0;
			for (int t = 0; go[X][Y + t] == fa; t++) {
				count++;
				if ((Y + t + 1) > 15)
					break;
			}
			for (int t = 0; go[X][Y - t] == fa; t++) {
				count++;
				if ((Y - t - 1) < 0)
					break;
			}
			if (count >= 6) {
				flag = true;
			}
			// 右对角线
			count = 0;
			for (int t = 0; go[X + t][Y - t] == fa; t++) {
				count++;
				if ((X + t + 1) > 15 || (Y - t - 1) < 0)
					break;
			}
			for (int t = 0; go[X - t][Y + t] == fa; t++) {
				count++;
				if ((Y + t + 1) > 15 || (X - t - 1) < 0)
					break;
			}
			if (count >= 6) {
				flag = true;
			}
			// 左对角线
			count = 0;
			for (int t = 0; go[X - t][Y - t] == fa; t++) {
				count++;
				if ((X - t - 1) < 0 || (Y - t - 1) < 0)
					break;
			}
			for (int t = 0; go[X + t][Y + t] == fa; t++) {
				count++;
				if ((X + t + 1) > 15 || (Y + t + 1) > 15)
					break;
			}
			if (count >= 6) {
				flag = true;
			}

			return flag;
		}// judge

		// 新的一局 重置棋盘//游戏结束弹出窗口
		public void replay(int tmp) {
			// 创建新窗口及其组件
			final JFrame winf = new JFrame();
			JPanel winp = new JPanel();
			JPanel winp1 = new JPanel();
			JLabel winl = new JLabel();
			final JButton winb = new JButton("重新开始");

			winl.setFont(new Font("黑体", Font.PLAIN, 32));// 设置标签字体
			winf.setLocation(800, 300);// 设置窗口位置
			winf.setSize(500, 200);// 设置大小
			winf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 后台关闭
			winf.setResizable(false);// 不可改变大小
			winf.setTitle("游戏结束");// 窗口标题
			winf.setVisible(true);// 窗口可见

			// 创建GridLayout布局 指定几行几列
			GridLayout g = new GridLayout(2, 1);
			winf.setLayout(g);
			// 设置标签显示
			if (tmp == 1) {
				winl.setText("黑方胜利")/* add(new JLabel("黑方胜利",JLabel.CENTER)) */;
			} else {
				winl.setText("白方胜利")/*
									 * winp.add(new
									 * JLabel("白方胜利",JLabel.CENTER))
									 */;
			}

			// 将组件添加到窗口
			winp.add(winl);
			winp1.add(winb);
			winf.add(winp);
			winf.add(winp1);

			// 重置落子计数和棋盘
			fallcount = 1;
			for (int k = 0; k < 16; k++) {
				for (int j = 0; j < 16; j++) {
					go[k][j] = 0;
				}
			}

			// 为按钮添加事件监听
			winb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == winb) {
						p1.updateUI();
						winf.dispose();
					}
					// else if(e.getSource()==winb1){
					// System.exit(0);
					// }
				}
			});

		}
	}

	public void init() {
		JFrame f = new JFrame();

		f.setBackground(new Color(222, 184, 135));// 设置背景颜色
		p1.addMouseListener(p1);// 为面板添加鼠标监听
		f.add(p1);// 将面板添加到窗体
		f.setSize(900, 900);// 设置窗口大小
		f.setLocation(500, 50);// 设置窗口位置
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 后台关闭
		f.setResizable(false);// 不可改变大小
		f.setTitle("五子棋");// 窗口标题
		f.setVisible(true);// 窗口可见
	}

	public static void main(String[] args) {
		gobang gb = new gobang();
		gb.init();
	}
}