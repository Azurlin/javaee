package com.Tfishe.day01;

import java.awt.image.BufferedImage;


public class Fishd {
	//定义一个鱼的图片
	BufferedImage fishImg;
	//定义鱼图片的宽和高
	int fishW,fishH;
	//定义鱼游动的图片数
	int fishNum;
	//鱼游动的图片数组
	BufferedImage fishs[];
	//每条鱼的坐标及速度
	int fishX,fishY,fishSpeed;
	//每一条鱼的是否被捕住
	public boolean isCatch = false;// 是否被捕
	public Fishd(){}
	public Fishd(BufferedImage fishImg,int fishW,int fishH,int fishNum){
		this.fishImg=fishImg;
		this.fishW = fishW;
		this.fishH = fishH;
		this.fishNum = fishNum;
		this.fishX=-(int)(Math.random()*20)+1;
		fishs = new BufferedImage[fishNum];
		//通过循环把每个图片加入数组
		for(int i=0;i<fishNum;i++){
			fishs[i]=fishImg.getSubimage(0, i * fishH, fishW, fishH);
		}
		//生成鱼的坐标
		fishY=(int)(Math.random()*300)+1;
		//生成鱼的速度
		fishSpeed = (int)(Math.random()*6)+1;
		
	}
	public BufferedImage getFishImg() {
		return fishImg;
	}
	public int getFishW() {
		return fishW;
	}
	public int getFishH() {
		return fishH;
	}
	public int getFishNum() {
		return fishNum;
	}
	public BufferedImage[] getFishs() {
		return fishs;
	}
	public int getFishX() {
		return fishX;
	}
	public int getFishY() {
		return fishY;
	}
	public int getFishSpeed() {
		return fishSpeed;
	}
	public boolean isCatch() {
		return isCatch;
	}
	
	public void setCatch(boolean isCatch) {
		this.isCatch = isCatch;
	}
	//用于更改鱼的坐标信息
	public void setFishX(int fishX) {
		this.fishX = fishX;
	}
	public void setFishY(int fishY) {
		this.fishY = fishY;
	}
	
}

