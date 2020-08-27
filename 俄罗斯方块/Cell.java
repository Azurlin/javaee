package com.lddx.Tetris;

public class Cell {
	//属性——特征
	int row; //行
	int col;//列
	
	public Cell(int r,int c){
		//System.out.println("构造方法执行");
		row=r;
		col=c;		
	}
	
	/*public Cell(int row,int col){
		//当参数的变量名和属性/成员变量的名字一致 需要用this区分
		//this.row指的是当前对象row 是属性
		//row指的是参数变量
		this.row=row;
		this.col=col;
	}*/
	//下落
	public void drop(){
		row++;
	}
	//左移
	public void right(){
		col++;
	}
	//右移
	public void left(){
		col--;
		
	}
	public int getrow(){
		return row;
	}
	public int getcol(){
		return col;
	}
	public void printInfo(){
		System.out.print(row+","+col);
	}
}
