package Test_Gobang;
import java.util.Scanner;
//������
/* 1.��������
 * 2.��ӡ����
 * 3.����
 * 4.�ж�ʤ��
 */
public class TestGobang {
	public static void main(String[] args) {
		char[][] Gobang=new char[16][16];
		creatCB(Gobang);
		printCB(Gobang);
		Thefall(Gobang);	
}
	//4.�ж�ʤ��
	public static boolean Judge (char go[][],int X,int Y){
		boolean flag=false;
		char fa=go[X][Y];
		int count=0;
		//����
		for(int t=0;go[X+t][Y]==fa;t++){count++;if((X+t+1)>14)break;}
		for(int t=0;go[X-t][Y]==fa;t++){count++;if((X-t-1)<0)break;}
		if(count >= 6){flag = true;}		
		//����
		count=0;
		for(int t=0;go[X][Y+t]==fa;t++){count++;if((Y+t+1)>14)break;}
		for(int t=0;go[X][Y-t]==fa;t++){count++;if((Y-t-1)<0)break;}
		if(count >= 6){flag = true;}
		//�ҶԽ���
		count=0;
		for(int t=0;go[X+t][Y-t]==fa;t++){count++;if((X+t+1)>14||(Y-t-1)<0)break;}
		for(int t=0;go[X-t][Y+t]==fa;t++){count++;if((Y+t+1)>14||(X-t-1)<0)break;}
		if(count >= 6){flag = true;}
		//��Խ���
		count=0;
		for(int t=0;go[X-t][Y-t]==fa;t++){count++;if((X-t-1)<0||(Y-t-1)<0)break;}
		for(int t=0;go[X+t][Y+t]==fa;t++){count++;if((X+t+1)>14||(Y+t+1)>14)break;}
		if(count >= 6){flag = true;}
		return flag;
	}		
	//3.����
	public static void Thefall(char go[][]){
		Scanner sc=new Scanner(System.in);
			int x,y;
		while(true){
			
			//----------------------------------------------------�ڷ��غ�-----------------------------------------------------------
			System.out.println("�ڷ����ӣ�");
			while(true){
				String src=sc.next();
				x=src.charAt(0);
				y=src.charAt(1);
				if(x>=97){x=x-87;}else{x=x-48;}
				if(y>=97){y=y-87;}else{y=y-48;}
				if(x>=16||y>=16||x<0||y<0){System.out.println("��Чλ��������");continue;}//�ж���Чλ��
				//�ж��ظ�
				if(go[x][y]=='*'){ 
					go[x][y]='@';}
				else{
					System.out.println("��λ���������������£�\n");
					continue;
				}
				printCB(go);//��ӡ����			
				break;
				}
			if(Judge(go,x,y)){System.out.println("�ڷ�ʤ��");break;}//��ʤ
			
			//----------------------------------------------------�׷��غ�-----------------------------------------------------------
			System.out.println("�׷����ӣ�");
			while(true){	
				String src=sc.next();
				x=src.charAt(0);
				y=src.charAt(1);
				if(x>=97){x=x-87;}else{x=x-48;}
				if(y>=97){y=y-87;}else{y=y-48;}
				if(x>=16||y>=16||x<0||y<0){System.out.println("��Чλ��������");continue;}//�ж���Чλ��
				//�ж��ظ�
				if(go[x][y]=='*'){
					go[x][y]='#';}
				else{
					System.out.println("��λ���������������£�\n");
					continue;
				}
				printCB(go);//��ӡ����
				break;
			}
			if(Judge(go,x,y)){System.out.println("�׷�ʤ��");break;}//��ʤ
		}
		}
	//2.��ӡ����
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
	//1.��������(�µĶԾ�)
	public static char[][] creatCB(char go[][]){
		for(int i=0;i<go.length;i++){
			for(int j=0;j<go.length ;j++)
				go[i][j]='*';	
		}
		return go;
}
}