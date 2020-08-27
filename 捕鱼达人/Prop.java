package com.Tfishe.day01;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Prop {
	int x;
	int y;
	Image icon ;
	private GameJPanel panel;
	
	public Prop(int x,int y,GameJPanel panel) {
		this.x = x;
		this.y = y;		 
		icon =new ImageIcon("images/xiandanIcon.png").getImage();
		this.panel = panel;
	}
	public void drawprop(Graphics g){
		g.drawImage(icon, x, y, null);
	}
	
	public void startprop(){
		   new Thread(){
			   public void run(){
				   while(true){
					   y+=3;
					   if (y <= panel.getHeight()-20) {	
						   panel.pp.remove(this);
					   }
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   }
			   }
		   }.start();
	
	}}

