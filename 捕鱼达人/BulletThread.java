package com.Tfishe.day01;

public class BulletThread extends Thread {
	public Bullet bullet;
	//	¹¹Ôìº¯Êý
	public BulletThread(Bullet bullet){
		this.bullet = bullet;
	}
	public void run(){
		while(bullet.isLive){
			bullet.move();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}




}
