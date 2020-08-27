package com.Tfishe.day01;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


//�ڵ�
public class Bullet {
	public int x;//��������
	public int y;
	int bx = 0;
	int by = 0;
	int bx1=0;
	int by1=0;
	int bx2 = 0;
	int by2 = 0;
	Point p;
	public ImageIcon img; //�ӵ�ͼƬ
	public double roate; //�ӵ��Ƕ�
	boolean isLive = true;//�����ж�
	public GameJPanel panel;
	private Point endPoint;//�ڵ���������
	int mx,my;
	Fish fish;
	public Bullet(GameJPanel panel){
		this.panel = panel;
	}
	
	//�ڵ��ƶ�
	public void move(){
		y -=20;
		int yy = Math.round((float) Math.cos(roate)*(p.y-17-y));
		by = p.y - 17 -Math.abs(yy);
		int xx = Math.round((float) Math.sin(roate)*(p.y-17-y));
		bx = p.x -10+xx;
		/*if(!panel.PJ){
		int yy1 = Math.round((float) Math.cos(roate)*(p.y-17-y));
		by1 = p.y - 17 -Math.abs(yy);
		int xx1 = Math.round((float) Math.sin(roate)*(p.y-17-y));
		bx1 = p.x -10+xx;
		
		int yy2 = Math.round((float) Math.cos(roate)*(p.y-17-y));
		by2 = p.y - 17 -Math.abs(yy);
		int xx2 = Math.round((float) Math.sin(roate)*(p.y-17-y));
		bx2 = p.x -10+xx;
		
		}*/
		
		//�ж��ڵ��Ƿ����
		if(by <=-10 || bx <= -10){
			isLive = false;
			panel.bullets.remove(this);
		}
		hint();
		
	}
	// �������ĳ���� ����
	public void hint() {
		for (int j = 0; j < panel.bullets.size(); j++) {
			for (int i = 0; i < panel.flist.size(); i++) {
				Fishd f = panel.flist.get(i);
				if (bx >= f.getFishX() && bx <= f.getFishX() + f.getFishW()
						&& by >= f.getFishY()
						&& by <= f.getFishY() + f.getFishH()) {
					f.isCatch=true;
					Web web = new Web();
					try {
						if(panel.PJ){//�ж��Ƿ�Ϊ�����ӵ�
						web.setImage(ImageIO.read(new File("images/web"+panel.nbarrel+".png")));
					}else{
						web.setImage(ImageIO.read(new File("images/XXX.png")));//�����ӵ���Ч��
					}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					web.setX(bx - 80);
					web.setY(by - 80);
					panel.webs.add(web);
					isLive = false;
					panel.bullets.remove(this);
					this.startWeb(web);	
				}
			}
		}

	}

	// ����һ���������������ĳ���ʱ��
	public void startWeb(final Web w) {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(300);
					panel.webs.remove(w);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	//�����ڵ�
	public void drawBullet(Graphics g){
		Graphics2D gp = (Graphics2D) g.create();
		
		//System.out.println(roate);
		
		/*gp.rotate(roate,p.x,p.y);
		gp.drawImage(this.img.getImage(),x,y,panel);*/
		/*if(roate>=0){
		gp.rotate(roate,p.x,p.y);
		gp.drawImage(this.img.getImage(),x,y,panel);
		gp.rotate(roate+roate/2,p.x,p.y);
		gp.drawImage(this.img.getImage(),x,y,panel);
		gp.rotate(roate-roate/2,p.x,p.y);
		gp.drawImage(this.img.getImage(),x,y,panel);
		}
		else{
			gp.rotate(roate,p.x,p.y);
			gp.drawImage(this.img.getImage(),x,y,panel);
			gp.rotate(roate-roate/2,p.x,p.y);
			gp.drawImage(this.img.getImage(),x,y,panel);
			gp.rotate(roate+roate/2,p.x,p.y);
			gp.drawImage(this.img.getImage(),x,y,panel);
			
		}*/
		
		/*for(int i=0;i<=5;i++){
			gp.rotate((roate),p.x+1,p.y+1);
			gp.drawImage(this.img.getImage(),x,y,panel);
		}*/
		//if(roate<=0){
		gp.rotate((roate),p.x,p.y);
		gp.drawImage(this.img.getImage(),x,y,panel);
		if(!panel.PJ){
		gp.rotate((roate),p.x,p.y);
		gp.drawImage(this.img.getImage(),x,y,panel);
		gp.rotate(0.5*roate,p.x,p.y);
		gp.drawImage(this.img.getImage(),x,y,panel);
		gp.rotate((-1*roate),p.x+1,p.y+1);
		gp.drawImage(this.img.getImage(),x,y,panel);
		}
		/*}else{

		gp.rotate((roate),p.x,p.y);
		gp.drawImage(this.img.getImage(),x,y,panel);
		gp.rotate(roate,p.x,p.y);
		gp.drawImage(this.img.getImage(),x,y,panel);
		gp.rotate(roate,p.x+1,p.y+1);
		gp.drawImage(this.img.getImage(),x,y,panel);
		}*/
		
	}
	
	public static void main(String[] args) {
		

	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(){
		this.x = x;
	}
	public void setY(){
		this.y = y;
	}
	public Point getEndPoint(){
		return endPoint;
	}
	public void setEndPoint(Point endPoint){
		this.endPoint = endPoint;
	}
	public int getbx(){
		return bx;
	}
	public int getby(){
		return by;
	}
	public void setbx(){
		this.bx = bx;
	}
	public void setby(){
		this.by = by;
	}
	
	

}
