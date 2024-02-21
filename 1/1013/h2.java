/* 10/13 楊育哲
 * 實作第二題: 計算兩點或點與直線間的距離
 */
import java.util.Scanner;
public class h2 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("輸入點座標(兩個數字): ");
        int x1=sc.nextInt(), y1=sc.nextInt();
        System.out.println("選擇'點'(0)或'線段'(1)");
        int flag=sc.nextInt();
        if(flag==1){
            System.out.println("輸入線段[ax+by=c](三個數字a、b、c): ");
            int a=sc.nextInt(), b=sc.nextInt(), c=sc.nextInt();
            if(a*a+b*b!=0) System.out.println("點與線段之間的距離為: "+Math.abs(a*x1+b*y1+c)/Math.sqrt(a*a+b*b));
            else System.out.println("點與線段之間的距離為: 0");
        }else{
            System.out.println("輸入點座標(兩個數字): ");
            int x2=sc.nextInt(), y2=sc.nextInt();
            System.out.println("兩點之間的距離為: "+Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)));
        }
    }
}
