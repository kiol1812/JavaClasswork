/* 10/27 楊育哲
 * 實作第一題: 將數組刪去重複項
 */
import java.util.Scanner;
public class h1_1027 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("輸入一數串(以空格分開):");
        String s=sc.nextLine();
        int L=1, current=-1;
        for(int i=0; i<s.length(); i++) if(s.charAt(i)==' ') L++;
        int[] arr=new int[L];
        int[] tmp = new int[L];
        for(int i=0; i<L; i++){
            int num=0;
            while(current<s.length()-1&&s.charAt(++current)!=' '){
                num = num*10+(int)(s.charAt(current)-'0');
            }
            arr[i] = num;
        }
        current=-1;
        for(int i=0; i<L; i++){
            boolean flag=true;
            for(int j=0; j<=current; j++){
                if(tmp[j]==arr[i]) flag=false;
            }
            if(flag) tmp[++current]=arr[i];
        }
        int[] result=new int[current+1];
        for(int i=0; i<=current; i++) result[i]=tmp[i];
        for(int i=0; i<current; i++) System.out.printf("%d, ", result[i]);
        System.out.println(result[current]);
    }
}