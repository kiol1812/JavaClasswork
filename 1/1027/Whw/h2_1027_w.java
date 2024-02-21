/* 1027 楊育哲 周作業
 * 實作第二題: 取一數串的子數串，確認其可以相加乘所求數字
 */
import java.util.Scanner;
public class h2_1027_w {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("輸入一數串(以空格分開):");
        String s=sc.nextLine();
        System.out.print("輸入一數字(target):");
        int L=1, current=-1, target=sc.nextInt();
        for(int i=0; i<s.length(); i++) if(s.charAt(i)==' ') L++;
        int[] arr=new int[L];
        for(int i=0; i<L; i++){
            int num=0;
            while(current<s.length()-1&&s.charAt(++current)!=' '){
                num = num*10+(int)(s.charAt(current)-'0');
            }
            arr[i] = num;
        }
        boolean match=false;
        int[][] ans=new int[L][];
        current=0;
        for(int i=0; i<L; i++){
            for(int j=i; j<L; j++){
                int num=0;
                for(int k=i; k<=j; k++) num+=arr[k];
                if(num==target){
                    match = true;
                    ans[current] = new int[j-i+2];
                    ans[current][0]=j-i+1;
                    for(int k=i; k<=j; k++) ans[current][k-i+1]=arr[k];
                    current++;
                }
            }
        }
        if(match){//若找到符合之子串，將全部符合的子字串都列印出
            for(int i=0; i<current; i++){
                for(int j=0; j<ans[i][0]-1; j++) System.out.printf("%d+", ans[i][j+1]);
                System.out.printf("%d=%d\n", ans[i][ans[i][0]], target);
            }
        }else System.out.println("No-match");
    }
}
