package com.lddx.Tetris;
public class O {
	//属性/成员变量/全局变量 == 特征
	Cell[] cells=new Cell[4];//定义一个大小为4的Cell类型的数组，表示该图形的特征
	//构造方法
	public O(){
		Cell c1=new Cell(0,4);//
		Cell c2=new Cell(0,5);
		Cell c3=new Cell(1,4);
		Cell c4=new Cell(1,5);     		
		cells[0]=c1;
		cells[1]=c2;
		cells[2]=c3;
		cells[3]=c4;
	}
	//方法 == 行为
	public void printShape(){
		for(int i=0;i<cells.length;i++){
			Cell c=cells[i];
			c.printInfo();
		}
	}
	//O图形左移的行为
	public void leftShape(){
		for(int i=0;i<cells.length;i++){
			Cell c=cells[i];
			c.left();
		}
	}
	//O图形右移的行为
	public void rightShape(){
		for(int i=0;i<cells.length;i++){
			Cell c=cells[i];
			c.right();
		}
	}
	//O图形下落的行为
	public void dropShape(){
		for(int i=0;i<cells.length;i++){
			Cell c=cells[i];
			c.drop();
		}
	}
	
	public Cell[] getGraph() {
		return cells;
	}
}
