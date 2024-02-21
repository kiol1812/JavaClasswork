/* 11/03 楊育哲
 * 實作第二題: 輸入多串數字，每輸入一數字，即輸出到目前為止的中位數
 */
import java.util.Scanner;
public class h2_1103 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int[] list = new int[10000];
        int value=0, top=0;
        while(true){
            value = sc.nextInt();
            if(value==99999) break;
            list[top++]=value;
            for(int i=1; i<top; i++){
                int key=list[i], j=i-1;
                while(j>=0&&key<list[j]){
                    list[j+1]=list[j];
                    j--;
                }
                list[j+1]=key;
            }
            // for(int i=0; i<top; i++) System.out.printf("%d ", list[i]);
            // System.out.printf(", %d\n", top);
            if(top%2==0) System.out.println((list[top/2-1]+list[top/2])/2);
            else System.out.println(list[top/2]);
        }
    }
}
