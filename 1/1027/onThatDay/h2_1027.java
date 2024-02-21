/* 10/27 楊育哲
 * 實作第二題:
 *     a. 輸出n階梯的'*'
 *     b. 輸出要找的數字在n下三角形的座標，第一排(列)始以1上數
*/
import java.util.Scanner;
public class h2_1027 {
    public static void a(int n){
        char arr[][]=new char[n][];
        for(int i=0; i<n; i++){
            arr[i]=new char[i+1];
            for(int j=0; j<=i; j++){
                arr[i][j]='*';
                System.out.print(arr[i][j]);
            }
            System.out.println("");
        }
    }
    public static void b(int n, int target){
        int arr[][]=new int[n][];
        int num=1, r=0, c=0;
        boolean find=false;
        for(int i=0; i<n; i++){
            arr[i]=new int[i+1];
            for(int j=0; j<=i; j++){
                if(target==num){
                    r = i+1;
                    c = j+1;
                    find = true;
                }
                arr[i][j]=num++;
            }
        }
        if(find) System.out.printf("r=%d, c=%d\n", r, c);
        else System.out.println("not find.");
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("輸入一數字n: ");
        int n=sc.nextInt();
        a(n);
        System.out.print("輸入一數字n: ");
        n=sc.nextInt();
        System.out.print("輸入一數字target: ");
        int target=sc.nextInt();
        b(n, target);
    }
}
