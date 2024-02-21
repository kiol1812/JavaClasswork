/* 11/03 楊育哲
 * 實作第三題: 擬約瑟夫問題, 17區，n=2~10
 */
import java.util.Scanner;
public class h3_1103 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int[] list = new int[17];
        int[] listAfter = new int[17];
        int current=0, index=0;
        for(int i=0; i<17; i++) list[i] = i+1;
        System.out.print("輸入數字n(2~10), 表示每格n區停電: ");
        int n = sc.nextInt();
        while(index<17){
            listAfter[index] = list[current];
            list[current] = 0;
            int steps=n;
            while(steps>0){
                current=(current+1)%17;
                while(list[current]==0&&index<16) current=(current+1)%17;
                steps--;
            }
            index++;
        }
        for(int i=0; i<17; i++) System.out.printf("%d ", listAfter[i]);
    }
}
