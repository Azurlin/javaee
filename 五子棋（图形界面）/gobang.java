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

//������
public class gobang {
	ppaint p1 = new ppaint();

	public class ppaint extends JPanel implements MouseListener {
		private Graphics g;
		private int fallcount = 1; // ��������˳��
		private int[][] go = new int[16][16];

		public void paint(Graphics g) {
			int c = 50;
			// ��������
			g.setColor(Color.BLACK);
			for (int i = 0; i < 16; i++) {
				g.drawLine(50, c, 800, c);// ������
				g.drawLine(c, 50, c, 800);// ������
				c = 50 + c;
			}
		}// paint

		// ����¼�����
		public void mouseClicked(MouseEvent e) {
			double x, y, ix, dx, iy, dy;
			if (e.getButton() == e.BUTTON1) {// �жϻ�ȡ�İ�ť�Ƿ�Ϊ�������
				x = e.getX();
				y = e.getY(); // ��ȡ�����xy����
				if (x >= 50 && x <= 800 && y >= 50 && y <= 800) {// �ж�������Ƿ�Խ��
					// System.out.println("������꣺"+"X:"+x+"Y:"+y);
					Graphics g = getGraphics(); // ��ȡ��ͼ���

					// ��������
					ix = Math.floor(x / 50); // ����ȡ����ȡ��������
					iy = Math.floor(y / 50);// *
					dx = (x / 50 - ix); // ��ȡС������
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
					// �ж��ظ� �������Ӳ��ж�ʤ��
					if (go[(int) x / 50 - 1][(int) y / 50 - 1] == 0) { // �ж��Ƿ��ظ�����
						// ��������Ϣ��������
						// ��������
						fallcount++;
						if (fallcount % 2 == 0) {// ������ɫ
							g.setColor(Color.BLACK);
							go[(int) x / 50 - 1][(int) y / 50 - 1] = 1;
						} else {
							g.setColor(Color.WHITE);
							go[(int) x / 50 - 1][(int) y / 50 - 1] = 2;
						}
						g.fillOval((int) x - 25, (int) y - 25, 45, 45);
						// �ж�ʤ��
						if (judge((int) x / 50 - 1, (int) y / 50 - 1)) {
							if (fallcount % 2 == 0) {
								System.out.println("�ڷ�ʤ��");
								replay(1);
							} else {
								System.out.println("�׷�ʤ��");
								replay(0);
							}
						}
					} else {
						System.out.println("��λ���������������£�\n");
					}
					//System.out.println("���������꣺" + "X:" + x + "Y:" + y);
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

		// �ж�ʤ������
		public boolean judge(int X, int Y) {
			boolean flag = false;
			int fa = go[X][Y];
			int count = 0;
			// ����
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
			// ����
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
			// �ҶԽ���
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
			// ��Խ���
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

		// �µ�һ�� ��������//��Ϸ������������
		public void replay(int tmp) {
			// �����´��ڼ������
			final JFrame winf = new JFrame();
			JPanel winp = new JPanel();
			JPanel winp1 = new JPanel();
			JLabel winl = new JLabel();
			final JButton winb = new JButton("���¿�ʼ");

			winl.setFont(new Font("����", Font.PLAIN, 32));// ���ñ�ǩ����
			winf.setLocation(800, 300);// ���ô���λ��
			winf.setSize(500, 200);// ���ô�С
			winf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// ��̨�ر�
			winf.setResizable(false);// ���ɸı��С
			winf.setTitle("��Ϸ����");// ���ڱ���
			winf.setVisible(true);// ���ڿɼ�

			// ����GridLayout���� ָ�����м���
			GridLayout g = new GridLayout(2, 1);
			winf.setLayout(g);
			// ���ñ�ǩ��ʾ
			if (tmp == 1) {
				winl.setText("�ڷ�ʤ��")/* add(new JLabel("�ڷ�ʤ��",JLabel.CENTER)) */;
			} else {
				winl.setText("�׷�ʤ��")/*
									 * winp.add(new
									 * JLabel("�׷�ʤ��",JLabel.CENTER))
									 */;
			}

			// �������ӵ�����
			winp.add(winl);
			winp1.add(winb);
			winf.add(winp);
			winf.add(winp1);

			// �������Ӽ���������
			fallcount = 1;
			for (int k = 0; k < 16; k++) {
				for (int j = 0; j < 16; j++) {
					go[k][j] = 0;
				}
			}

			// Ϊ��ť����¼�����
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

		f.setBackground(new Color(222, 184, 135));// ���ñ�����ɫ
		p1.addMouseListener(p1);// Ϊ������������
		f.add(p1);// �������ӵ�����
		f.setSize(900, 900);// ���ô��ڴ�С
		f.setLocation(500, 50);// ���ô���λ��
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ��̨�ر�
		f.setResizable(false);// ���ɸı��С
		f.setTitle("������");// ���ڱ���
		f.setVisible(true);// ���ڿɼ�
	}

	public static void main(String[] args) {
		gobang gb = new gobang();
		gb.init();
	}
}