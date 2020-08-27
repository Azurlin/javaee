package Test_Gobang;
import java.util.Scanner;
//五子棋
/* 1.创建棋盘
 * 2.打印棋盘
 * 3.落子
 * 4.判断胜利
 */
public class TestGobang {
	public static void main(String[] args) {
		char[][] Gobang=new char[16][16];
		creatCB(Gobang);
		printCB(Gobang);
		Thefall(Gobang);	
}
	//4.判断胜利
	public static boolean Judge (char go[][],int X,int Y){
		boolean flag=false;
		char fa=go[X][Y];
		int count=0;
		//横向
		for(int t=0;go[X+t][Y]==fa;t++){count++;if((X+t+1)>14)break;}
		for(int t=0;go[X-t][Y]==fa;t++){count++;if((X-t-1)<0)break;}
		if(count >= 6){flag = true;}		
		//纵向
		count=0;
		for(int t=0;go[X][Y+t]==fa;t++){count++;if((Y+t+1)>14)break;}
		for(int t=0;go[X][Y-t]==fa;t++){count++;if((Y-t-1)<0)break;}
		if(count >= 6){flag = true;}
		//右对角线
		count=0;
		for(int t=0;go[X+t][Y-t]==fa;t++){count++;if((X+t+1)>14||(Y-t-1)<0)break;}
		for(int t=0;go[X-t][Y+t]==fa;t++){count++;if((Y+t+1)>14||(X-t-1)<0)break;}
		if(count >= 6){flag = true;}
		//左对角线
		count=0;
		for(int t=0;go[X-t][Y-t]==fa;t++){count++;if((X-t-1)<0||(Y-t-1)<0)break;}
		for(int t=0;go[X+t][Y+t]==fa;t++){count++;if((X+t+1)>14||(Y+t+1)>14)break;}
		if(count >= 6){flag = true;}
		return flag;
	}		
	//3.落子
	public static void Thefall(char go[][]){
		Scanner sc=new Scanner(System.in);
			int x,y;
		while(true){
			
			//----------------------------------------------------黑方回合-----------------------------------------------------------
			System.out.println("黑方落子：");
			while(true){
				String src=sc.next();
				x=src.charAt(0);
				y=src.charAt(1);
				if(x>=97){x=x-87;}else{x=x-48;}
				if(y>=97){y=y-87;}else{y=y-48;}
				if(x>=16||y>=16||x<0||y<0){System.out.println("无效位置请重下");continue;}//判断无效位置
				//判断重复
				if(go[x][y]=='*'){ 
					go[x][y]='@';}
				else{
					System.out.println("该位置已有棋子请重下：\n");
					continue;
				}
				printCB(go);//打印棋盘			
				break;
				}
			if(Judge(go,x,y)){System.out.println("黑方胜利");break;}//黑胜
			
			//----------------------------------------------------白方回合-----------------------------------------------------------
			System.out.println("白方落子：");
			while(true){	
				String src=sc.next();
				x=src.charAt(0);
				y=src.charAt(1);
				if(x>=97){x=x-87;}else{x=x-48;}
				if(y>=97){y=y-87;}else{y=y-48;}
				if(x>=16||y>=16||x<0||y<0){System.out.println("无效位置请重下");continue;}//判断无效位置
				//判断重复
				if(go[x][y]=='*'){
					go[x][y]='#';}
				else{
					System.out.println("该位置已有棋子请重下：\n");
					continue;
				}
				printCB(go);//打印棋盘
				break;
			}
			if(Judge(go,x,y)){System.out.println("白方胜利");break;}//白胜
		}
		}
	//2.打印棋盘
	public static void printCB(char G[][]){
		System.out.print(" 0 1 2 3 4 5 6 7 8 9 a b c d e f");
		System.out.print("\n");
		for(int i=0;i<G.length;i++){
			if(i<=9)
				System.out.print(i);
			else
				System.out.print((char)(97+i-10));
			for(int j=0;j<G.length;j++){
				System.out.print(G[i][j]+" ");
			}
			System.out.print("\n");
		}
}
	//1.创建棋盘(新的对局)
	public static char[][] creatCB(char go[][]){
		for(int i=0;i<go.length;i++){
			for(int j=0;j<go.length ;j++)
				go[i][j]='*';	
		}
		return go;
}
}