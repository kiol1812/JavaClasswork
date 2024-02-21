/* 10/20 楊育哲
 * 實作第二題: 將輸入數字反轉輸出
 */
import java.util.Scanner;
public class h2_1020 {
    public static void main(String srgs[]){
        Scanner sc = new Scanner(System.in);
        int number=0, res=0;
        while(true){
            res=0;
            System.out.print("請輸入數字: ");
            number = sc.nextInt();
            if(number==0) break;
            while(number>0){
                res = res*10+number%10;
                number/=10;
            }
            System.out.println(res);
        }
    }
}
