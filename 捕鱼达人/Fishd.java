package com.Tfishe.day01;

import java.awt.image.BufferedImage;


public class Fishd {
	//����һ�����ͼƬ
	BufferedImage fishImg;
	//������ͼƬ�Ŀ�͸�
	int fishW,fishH;
	//�������ζ���ͼƬ��
	int fishNum;
	//���ζ���ͼƬ����
	BufferedImage fishs[];
	//ÿ��������꼰�ٶ�
	int fishX,fishY,fishSpeed;
	//ÿһ������Ƿ񱻲�ס
	public boolean isCatch = false;// �Ƿ񱻲�
	public Fishd(){}
	public Fishd(BufferedImage fishImg,int fishW,int fishH,int fishNum){
		this.fishImg=fishImg;
		this.fishW = fishW;
		this.fishH = fishH;
		this.fishNum = fishNum;
		this.fishX=-(int)(Math.random()*20)+1;
		fishs = new BufferedImage[fishNum];
		//ͨ��ѭ����ÿ��ͼƬ��������
		for(int i=0;i<fishNum;i++){
			fishs[i]=fishImg.getSubimage(0, i * fishH, fishW, fishH);
		}
		//�����������
		fishY=(int)(Math.random()*300)+1;
		//��������ٶ�
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
	//���ڸ������������Ϣ
	public void setFishX(int fishX) {
		this.fishX = fishX;
	}
	public void setFishY(int fishY) {
		this.fishY = fishY;
	}
	
}

