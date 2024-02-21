/* 10/13 楊育哲
 * 實作第一題: 不使用if-else，實作將三數排出min, mid, max
 */
import java.util.Scanner;
public class h1 {
    public static void main(String args[]){
        int a, b, c ,min, mid, max;
        Scanner sc = new Scanner(System.in);
        System.out.println("輸入三個數字");
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        // min = (min>mid||min>max) ? swap(min, mid) : min=min;
        min=(a<b) ? a:b;
        min=(min<c) ? min:c;
        max=(a<b) ? b:a;
        max=(max<c) ? c:max;
        mid=(a<b&&a>c||a>b&&a<c) ? a : ((b>a&&b<c||b<a&&b>c) ? b : c);
        System.out.println("min: "+min);
        System.out.println("mid: "+mid);
        System.out.println("max: "+max);
    }
}
