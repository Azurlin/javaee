package com.lddx.Tetris;
public class O {
	//����/��Ա����/ȫ�ֱ��� == ����
	Cell[] cells=new Cell[4];//����һ����СΪ4��Cell���͵����飬��ʾ��ͼ�ε�����
	//���췽��
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
	//���� == ��Ϊ
	public void printShape(){
		for(int i=0;i<cells.length;i++){
			Cell c=cells[i];
			c.printInfo();
		}
	}
	//Oͼ�����Ƶ���Ϊ
	public void leftShape(){
		for(int i=0;i<cells.length;i++){
			Cell c=cells[i];
			c.left();
		}
	}
	//Oͼ�����Ƶ���Ϊ
	public void rightShape(){
		for(int i=0;i<cells.length;i++){
			Cell c=cells[i];
			c.right();
		}
	}
	//Oͼ���������Ϊ
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
