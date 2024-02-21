/* 1117 楊育哲
 * 實作矩陣轉至
 */
import java.util.Scanner;
public class transpose {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int r=sc.nextInt(), c=0;
        sc.nextLine();
        int[][] array = new int[r][];
        String s;
        for(int i=0; i<r; i++){
            int count=1, item=0, current=0;
            s=sc.nextLine();
            for(int j=0; j<s.length(); j++) if(s.charAt(j)==' ')count++;
            c = (c<count)? count:c;
            array[i] = new int[count];
            for(int j=0; j<s.length(); j++){
                if(s.charAt(j)==' '){
                    array[i][current++] = item;
                    item = 0;
                }else item=item*10+(int)(s.charAt(j)-'0');
            }
            array[i][current] = item;
        }
        int[][] ans = new int[c][r];
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                try{
                    ans[j][i] = array[i][j];
                }catch(Exception e){
                    ans[j][i] = 0;
                }
            }
        }
        for(int i=0; i<c; i++){
            for(int j=0; j<r; j++) System.out.printf("%d ", ans[i][j]);
            System.out.println("");
        }
    }
}