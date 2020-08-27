package com.Tfishe.day01;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

//15 72
public class Money {
	int x;
	int y;
	GameJPanel panel;
     Image icon ;
     Image iconI;
     int temp = 0;
    int count = 0;
   public Money(int x, int y, GameJPanel panel) {
		this.x = x;
		this.y = y;
		icon = new ImageIcon("images/money.png").getImage();
		//iconI = new ImageIcon().getImage();
		this.panel = panel;
	}
   //定义一个方法用地画钱
   public void drawMoney(Graphics g){
			g.drawImage(icon, x, y, null);
		
   }
   //多线程用于金钱
   public void startMoney(){
	   new Thread(){
		   public void run(){
			   while(true){
				   y+=3;				   
	              if (y <= panel.getHeight()-20) {	     
						panel.ms.remove(this);
			    	}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   }
		   }
	   }.start();
   }
}

