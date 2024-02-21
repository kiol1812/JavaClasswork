/* 1027 楊育哲 周作業
 * 實作第一題: 找隨機r*c陣列裡鞍點
 */
import java.util.Scanner;
public class h1_1027_w {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int r=0, c=0, current=0;
        System.out.println("依序輸入兩個數字r, c:");
        r = sc.nextInt();
        c = sc.nextInt();
        int[][] arr=new int[r][c];
        int[][] saddlePoints=new int[r*c][2];
        boolean flag=false;
        while(!flag){//找到符合的二維陣列前重複執行
            for(int i=0; i<r; i++){
                for(int j=0; j<c; j++) arr[i][j]=(int)(Math.random()*201);
            }
            for(int i=0; i<r; i++){
                int min=201, minIndex=0;
                for(int j=0; j<c; j++){
                    if(arr[i][j]<min){
                        min=arr[i][j];
                        minIndex=j;
                    }
                }
                boolean check=true;
                for(int k=0; k<r; k++)
                    if(arr[k][minIndex]<min) check=false;
                if(check){//找到鞍點，while跳脫條件達成、將座標加入鞍點表
                    flag=true;
                    saddlePoints[current][0]=i;
                    saddlePoints[current++][1]=minIndex;
                }
            }
        }
        for(int i=0; i<r; i++){ //輸出目標二維陣列
            for(int j=0; j<c; j++) System.out.printf("%3d, ", arr[i][j]);
            System.out.println("");
        }
        for(int i=0; i<current; i++) //輸出所有saddle point
            System.out.printf("saddlePoint%d: arr[%d, %d]=%d\n", i+1, saddlePoints[i][0], saddlePoints[i][1], arr[saddlePoints[i][0]][saddlePoints[i][1]]);
    }
}