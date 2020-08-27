package com.lddx.Tetris;

public class Interface {
	private char[][] array = new char[15][10];
	public Interface(){
		for(int i=0;i<15;i++)
			for(int j=0;j<10;j++)
				array[i][j] = '=';
	}
	public char[][] getArray(){
		return array;
	}
	public void change(O g){
		for(int i=0;i<4;i++){
			int r = g.getGraph()[i].getrow();
			int c = g.getGraph()[i].getcol();
			array[r][c] = '#';
		}
	}
	public void putOutBoad(O g){//´òÓ¡½çÃæ
		for(int i=0;i<15;i++){
			for(int j=0;j<10;j++){
				boolean flag=true;
				for(int k=0;k<4;k++){
					int r = g.getGraph()[k].getrow();
					int c = g.getGraph()[k].getcol();
					if(i==r &&j==c){
						System.out.print('#');
						flag =false;
						break;
					}
				}
				if(flag == true){
					System.out.print(array[i][j]);
				}
			}
			System.out.println(); 	
		}
		System.out.println("^^^^^^^^^^^^^^^^^^"); 
	}	
	public static void main(String[] args) {

	}

}
