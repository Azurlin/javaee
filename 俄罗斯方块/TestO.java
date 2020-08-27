package com.lddx.Tetris;

import java.util.Scanner;

public class TestO {
	public static void main(String[] args) {
		O t=new O();
		Interface inf = new Interface();
		inf.putOutBoad(t);
		//t.printShape();	
		Scanner sc=new Scanner(System.in);
		int n;
		System.out.println("1下落2左移3右移0结束");
		//游戏开始
		while(true){
			n=sc.nextInt();
			if(n==0){System.out.println("游戏结束");break;}
			switch(n){
				case 1:t.dropShape();break;
				case 2:t.leftShape();break;
				case 3:t.rightShape();break;
			}
			inf.putOutBoad(t);
		}	
	}	
	}
	
