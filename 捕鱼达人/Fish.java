package com.Tfishe.day01;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Fish {
	Image en =new ImageIcon("images/bar01.png").getImage();//特殊子弹格
	//Image en =new ImageIcon("images/energy-bar01.png").getImage();
	//ImageIcon pimg = new ImageIcon("images/A4.png");
	int cc; //记录特殊子弹数量
	int temp;
	//存鱼张数
	private BufferedImage fishs[];
	//存鱼
	private BufferedImage fish;
	
	//实现循环
	int fishNum[] = {8, 8, 8, 8, 8, 10, 10, 12, 12, 12 };
	int boo[]= {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	
	//存鱼的宽高
	int fishWH[][] = { { 55, 37 }, { 78, 64 }, { 72, 56 }, { 77, 59 },
			{ 107, 122 }, { 92, 151 }, { 178, 187 }, { 105, 79 }, { 174, 126 },
			{ 166, 183 } };
	//金币
	int gold[] = {1,2,3,4,5,6,7,8,9,10};
	//鱼的坐标
	static int fishX = -10 ,fishY;
	GameJPanel panel;
	
	public Fish(GameJPanel panel){
		this.panel = panel;
		addFish();
	}
	//加载鱼
	public void addFish(){
		Fishd fd = new Fishd();
		for(int i = 0;i<fishNum.length;i++){
			try {
				fish = ImageIO.read(new File("images/fish" + (i + 1) + ".png"));
				Fishd fs = new Fishd(fish,fishWH[i][0],fishWH[i][1],fishNum[i]);
				panel.flist.add(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// 画鱼的方法
	public void drawFish(Graphics g) {
		// 通过循环画每一条鱼
		for (int i = 0; i < panel.flist.size(); i++) {
			// 画鱼
			if (!panel.flist.get(i).isCatch) {
				if (boo[i] == fishNum[i] - 4||boo[i]==fishNum[i]) {
					boo[i] = 0;
				}
				// 画每一条鱼的动画
				g.drawImage(panel.flist.get(i).getFishs()[boo[i]], panel.flist
						.get(i).getFishX(), panel.flist.get(i).getFishY(), null);
			} else { // 鱼捕到后的效果
				if (boo[i] == fishNum[i] || boo[i] < fishNum[i] - 4) {
					boo[i] = fishNum[i] - 4;
				}
				g.drawImage(panel.flist.get(i).getFishs()[boo[i]], panel.flist
						.get(i).getFishX(), panel.flist.get(i).getFishY(), null);
				//删除鱼后显示钱数
				if (boo[i] + 1 == fishNum[i]) {

					int fx=panel.flist.get(i).fishX;
					int fy=panel.flist.get(i).fishY;
					panel.flist.remove(i);
					boo[i] = 0;
					//将捕到鱼的金币加入炮台
					int golds=Integer.parseInt(panel.strGold);	
					panel.strGold=golds+gold[i]+"";
					Money m=new Money(fx, fy, panel);
					panel.ms.add(m);
					m.startMoney();
					
					drawPorpB(fx,g);

					
				}
			}

			boo[i]++;
			if(panel.flist.size()==0)addFish();

		}

	}
	//道具掉落
	public void drawPorpB(int fx,Graphics g){	
		//g.drawImage(panel.en, 559, 452, panel);
		
		if(temp<=10){
			temp++;//计数掉落
		
		if(temp>=5&&cc<=10){
			panel.enm.add(en);
			cc++;
			Prop p = new Prop(fx,0,panel);
			panel.pp.add(p);
			p.startprop();
						
			temp=0;
			
		}
		}
	}

	// 定义一个方法用于鱼游
	public void moveFish() {
		for (int i = 0; i < panel.flist.size(); i++) {
			panel.flist.get(i).setFishX(
					panel.flist.get(i).getFishX()
							+ panel.flist.get(i).getFishSpeed());
			if (panel.flist.get(i).getFishX() >= 800) {
				panel.flist.get(i).setFishX(-panel.flist.get(i).getFishW());
				panel.flist.get(i).setFishY((int) (Math.random() * 400));
			}
		}
	}

	public BufferedImage[] getFishs() {
		return fishs;
	}

	public void setFishs(BufferedImage[] fishs) {
		this.fishs = fishs;
	}

	public BufferedImage getFish() {
		return fish;
	}

	public void setFish(BufferedImage fish) {
		this.fish = fish;
	}

	public int getFishX() {
		return fishX;
	}

	public void setFishX(int fishX) {
		this.fishX = fishX;
	}

	public int getFishY() {
		return fishY;
	}

	public void setFishY(int fishY) {
		this.fishY = fishY;
	}

}

	
	
	
	
	

