package com.Tfishe.day01;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class GameJPanel extends JPanel implements MouseListener,MouseMotionListener
{
	Image M = new ImageIcon("images/MMM.png").getImage();
	//�����ڵ�
	int cen=559;
	ImageIcon pimg = new ImageIcon("images/A4.png");//�����ӵ�
	Image en =new ImageIcon("images/bar01.png").getImage();//���߼�����
	Vector<Image> enm = new Vector<Image>();//��ÿ������
	boolean PJ = true;
	Vector<Prop> pp = new Vector<Prop>();//����ߵ���
	Fish fish;
	//��
	private BufferedImage fishd;
	//������
	//Fish fish;
	Image bgImg = null;//��������ͼ
	Image bgImg1 = null;//�ײ�UI
	BufferedImage barrel;//��Ͳ
	int nbarrel;//�ܵ�����
	double roate;
	ImageIcon pd = new ImageIcon("images/bullet1.png");//�ڵ�
	private int fw,fh;//�����Ŀ��
	private BufferedImage goldImg;//���ڴ���
	private BufferedImage golds[] = new BufferedImage[10];//�����
	public int barrelX;//��ͲͼƬ�ĺ�������
	public int barrelY;
	String strgold = "999";//�����
	int goldi[];//��ÿλ���֣�����ȡ����ͼƬ
	private int fx = 200,fy = 100,num = 0;//�㿪ʼ������
	// ����һ����������װ��
	List<Fishd> flist = new ArrayList<Fishd>();
	//���ӵ�
	Vector<Bullet> bullets = new Vector<Bullet>();
	int score = 10;
	// ����һ���������ڴ�����
	List<Web> webs = new ArrayList<Web>();
	//���ڴ���
	Vector<Money> ms = new Vector<Money>();
	
	int mx,my;//�����ʱ������
	//�ı���Ͳ�ȼ��İ�ť
	Image btnA = new ImageIcon("images/cannon_plus_down.png").getImage();
	Image btnS = new ImageIcon("images/cannon_minus_down.png").getImage();
	String strGold ="9999";
	public GameJPanel(int width, int height) {
		//
		fw = width;
		fh = height;
		barrelX = 416;
		barrelY = this.fh - 62;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		fish = new Fish(this);						
		//bgImg1 = new ImageIcon("images/bottom-bar.png").getImage();
		
		try {
			goldImg = ImageIO.read(new File("images/coinText.png"));
			bgImg = ImageIO.read(new File("images/bg_03.png"));
			bgImg1= ImageIO.read(new File("images/bottom-bar.png"));			
			barrel = ImageIO.read(new File("images/cannon1.png")).getSubimage(0, 0, 74, 74);
			nbarrel = 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getGold();
		
	}

	
	//Paint ��ͼ���� ͨ�����ʻ��Ʊ���ͼƬ Graphics ����
	public void paint(Graphics g){
		super.paint(g);

		
		g.drawImage(bgImg, 0, 0, null);
		g.drawImage(bgImg1, 15, this.fh - 72, this);
		g.drawImage(btnS,360,450,this);
		g.drawImage(btnA,470,450,this);
		
		
		//�����߼�����
		for(int j=0;j <enm.size();j++){
			g.drawImage(enm.get(j), cen+j*19, 452, this);
		}
		
		// ѭ����װ���
		goldi = new int[strGold.length()];
		for (int i = 0, j = 0; i < strGold.length(); i++, j++) {
			int gold = Integer.parseInt(strGold.substring(i, i + 1));
			goldi[j] = gold;
		}
		// �������
		for (int i = 0; i < goldi.length; i++) {
			g.drawImage(golds[goldi[i]], 34 + i * 23, 455, 20, 18, this);			
		}
		//���û���ĺ���
		fish.drawFish(g);
		// ���ӵ�
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			b.drawBullet(g);
			if (webs.size()!= 0) {
				bullets.remove(i);
			}
		}
		// ������
		for (int i = 0; i < webs.size(); i++) {
			webs.get(i).drawWeb(g, this);
		}
		
		for (int i = 0; i < pp.size(); i++) {
			pp.get(i).drawprop(g);
		}
		
		// ��Ǯ
		for (int i = 0; i < ms.size(); i++) {
				ms.get(i).drawMoney(g);			
		}
		//����׼��
		drawM(g);
		drawBarrel(g);
		//repaint();
	}
	
	//����׼�� ������ƶ�
	public void drawM(Graphics g){		
		g.drawImage(M,mx-55,my-50,this);
		
	}
	
	public void getGold() {
		for (int i = 0; i < 10; i++) {
			try {
				golds[i] = ImageIO.read(new File("images/" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// ����˶�
	public void startFish() {
		new Thread() {
			public void run() {
				while (true) {
					fish.moveFish();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
				}// endwhile
			}
		}.start();
	}

	//������ת
	public void drawBarrel(Graphics g){
		int x = barrelX + 39;
		int y = barrelY + 39;
		double xx = (double) my - y;
		double yy = (double) mx - x;
		double f = 0.0;
		f = -Math.atan(yy/xx);
		if (f<=-Math.PI/2)
			f=-Math.PI/2;
		if (f>=Math.PI/2)
			f=Math.PI/2;
		roate = f;
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(f, x-15, y);
		g2.drawImage(barrel, barrelX - 15, barrelY, this);
	}
	
	//��תͼƬ
	public static BufferedImage rotateImage(final BufferedImage bufferedimage,final int degree){
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight() + 10;
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, type)).createGraphics())
		.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(degree),w/2,h/2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		return img;
	}
	//���ͼƬ��С
	public static BufferedImage resizeImage(final BufferedImage bufferedimage,
			final int w, final int h) {
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, type)).createGraphics())
				.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.drawImage(bufferedimage, 0, 0, w, h, 0, 0,
				bufferedimage.getWidth(), bufferedimage.getHeight(), null);
		graphics2d.dispose();
		return img;
	}
	//ˮƽ��ת
	public static BufferedImage flipImage(final BufferedImage bufferedimage) {
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, bufferedimage
				.getColorModel().getTransparency())).createGraphics())
				.drawImage(bufferedimage, 0, 0, w, h, w, 0, 0, h, null);
		graphics2d.dispose();
		return img;
	}
		
	@Override
	public void mouseMoved(MouseEvent e) {
		// ����ƶ�ʱ ��ȡ����
		this.mx = e.getX();
		this.my = e.getY();
		
	}
	//�ı����
	@Override
	public void mouseClicked(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		if(x>360&&x<404&&y>450&&y<481){//��С
			try {
				switch(nbarrel){
				case 3:	
					barrel = ImageIO.read(new File("images/cannon2.png")).getSubimage(0,0,74,76);
					pd = new ImageIcon("images/bullet2.png");
					nbarrel = 2;
					break;
				case 2:
					barrel = ImageIO.read(new File("images/cannon1.png")).getSubimage(0, 0, 74, 74);
					pd = new ImageIcon("images/bullet1.png");
					nbarrel = 1;
					break;
				}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		if(x>470&&x<514&&y>450&&y<481){//����
			try {
				switch(nbarrel){
				case 1:
					barrel = ImageIO.read(new File("images/cannon2.png")).getSubimage(0,0,74,76);
					pd = new ImageIcon("images/bullet2.png");
					nbarrel = 2;
					break;
				case 2:
					barrel = ImageIO.read(new File("images/cannon3.png")).getSubimage(0, 0, 74, 74);
					pd = new ImageIcon("images/bullet3.png");
					nbarrel = 3;
					break;
				}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
		
	

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
		
		Bullet bullet = new Bullet(GameJPanel.this);
		if(e.getButton() == MouseEvent.BUTTON1) {
			// ������
			PJ = true;
			bullet.img = pd;
		} else if(e.getButton() == MouseEvent.BUTTON3 ) {
			// �Ҽ����
			
			
			if(fish.cc>=1){
				PJ = false;
				bullet.img = pimg;
				enm.remove(fish.cc-1);
				fish.cc--;			
			}else{
				bullet.img = pd;
				PJ = true;
			}
		}
		// ȡ�������ʱ������
		mx = e.getX();
		my = e.getY();
		if (e.getY() < this.fh - 50) {
						
			bullet.mx = mx;
			bullet.my = my;
			bullet.x = barrelX + 19 - 10;
			bullet.y = barrelY + 39 - 17;
			bullet.roate = roate;
			bullet.p = new Point(barrelX + 19, barrelY + 39);
			bullets.add(bullet);
			Point p = new Point(mx, my);
			bullet.setEndPoint(p);
			BulletThread t = new BulletThread(bullet);
			t.start();
			score--;
			// ��������Ľ�Ҽ�����̨
			int golds = Integer.parseInt(strGold);
			strGold = golds - nbarrel + "";
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
