package com.lddx.Tetris;

public class Cell {
	//���ԡ�������
	int row; //��
	int col;//��
	
	public Cell(int r,int c){
		//System.out.println("���췽��ִ��");
		row=r;
		col=c;		
	}
	
	/*public Cell(int row,int col){
		//�������ı�����������/��Ա����������һ�� ��Ҫ��this����
		//this.rowָ���ǵ�ǰ����row ������
		//rowָ���ǲ�������
		this.row=row;
		this.col=col;
	}*/
	//����
	public void drop(){
		row++;
	}
	//����
	public void right(){
		col++;
	}
	//����
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
