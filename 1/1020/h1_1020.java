/* 10/20 楊育哲
 * 實作第一題: 檢查數字是否由左到右由小到大
 * 補1~1000有幾個well-ordered number
 */
import java.util.Scanner;
public class h1_1020 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int number=0, each=0;
        boolean flag=true;
        while(true){
            System.out.print("請輸入數字: ");
            number = sc.nextInt();
            flag = true;
            if(number==0) break;
            while(number>0&&flag){
                each = number%10;
                number/=10;
                if(each-number%10<0) flag=false;
            }
            if(flag) System.out.println("It's well-ordered number");
            else System.out.println("It's not well-ordered number");
        }
    }
}
