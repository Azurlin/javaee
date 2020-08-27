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
	//道具炮弹
	int cen=559;
	ImageIcon pimg = new ImageIcon("images/A4.png");//特殊子弹
	Image en =new ImageIcon("images/bar01.png").getImage();//道具计数条
	Vector<Image> enm = new Vector<Image>();//存每格能量
	boolean PJ = true;
	Vector<Prop> pp = new Vector<Prop>();//存道具掉落
	Fish fish;
	//鱼
	private BufferedImage fishd;
	//创建鱼
	//Fish fish;
	Image bgImg = null;//鱼塘背景图
	Image bgImg1 = null;//底部UI
	BufferedImage barrel;//炮筒
	int nbarrel;//跑的种类
	double roate;
	ImageIcon pd = new ImageIcon("images/bullet1.png");//炮弹
	private int fw,fh;//鱼塘的宽高
	private BufferedImage goldImg;//用于存金币
	private BufferedImage golds[] = new BufferedImage[10];//金币数
	public int barrelX;//炮筒图片的横纵坐标
	public int barrelY;
	String strgold = "999";//金币数
	int goldi[];//存每位数字，用于取数字图片
	private int fx = 200,fy = 100,num = 0;//鱼开始的坐标
	// 定义一个集合用于装鱼
	List<Fishd> flist = new ArrayList<Fishd>();
	//存子弹
	Vector<Bullet> bullets = new Vector<Bullet>();
	int score = 10;
	// 定义一个集合用于存鱼网
	List<Web> webs = new ArrayList<Web>();
	//用于存金币
	Vector<Money> ms = new Vector<Money>();
	
	int mx,my;//鼠标点击时的坐标
	//改变炮筒等级的按钮
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

	
	//Paint 画图方法 通过画笔绘制背景图片 Graphics 画笔
	public void paint(Graphics g){
		super.paint(g);

		
		g.drawImage(bgImg, 0, 0, null);
		g.drawImage(bgImg1, 15, this.fh - 72, this);
		g.drawImage(btnS,360,450,this);
		g.drawImage(btnA,470,450,this);
		
		
		//画道具计数器
		for(int j=0;j <enm.size();j++){
			g.drawImage(enm.get(j), cen+j*19, 452, this);
		}
		
		// 循环给装金币
		goldi = new int[strGold.length()];
		for (int i = 0, j = 0; i < strGold.length(); i++, j++) {
			int gold = Integer.parseInt(strGold.substring(i, i + 1));
			goldi[j] = gold;
		}
		// 画金币数
		for (int i = 0; i < goldi.length; i++) {
			g.drawImage(golds[goldi[i]], 34 + i * 23, 455, 20, 18, this);			
		}
		//调用画鱼的函数
		fish.drawFish(g);
		// 画子弹
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			b.drawBullet(g);
			if (webs.size()!= 0) {
				bullets.remove(i);
			}
		}
		// 画鱼网
		for (int i = 0; i < webs.size(); i++) {
			webs.get(i).drawWeb(g, this);
		}
		
		for (int i = 0; i < pp.size(); i++) {
			pp.get(i).drawprop(g);
		}
		
		// 画钱
		for (int i = 0; i < ms.size(); i++) {
				ms.get(i).drawMoney(g);			
		}
		//画瞄准镜
		drawM(g);
		drawBarrel(g);
		//repaint();
	}
	
	//画瞄准镜 随鼠标移动
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
	// 鱼的运动
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

	//大炮旋转
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
	
	//旋转图片
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
	//变更图片大小
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
	//水平翻转
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
		// 鼠标移动时 获取坐标
		this.mx = e.getX();
		this.my = e.getY();
		
	}
	//改变大炮
	@Override
	public void mouseClicked(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		if(x>360&&x<404&&y>450&&y<481){//减小
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
		if(x>470&&x<514&&y>450&&y<481){//增大
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
			// 左键点击
			PJ = true;
			bullet.img = pd;
		} else if(e.getButton() == MouseEvent.BUTTON3 ) {
			// 右键点击
			
			
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
		// 取得鼠标点击时的坐标
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
			// 将捕到鱼的金币加入炮台
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
