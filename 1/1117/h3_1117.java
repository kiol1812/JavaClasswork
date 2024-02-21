/* 1117 楊育哲
 * 實作第三題: 旋轉矩陣
 */
import java.util.Scanner;
public class h3_1117 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        while(--times>=0){
            int current=1, n=sc.nextInt(), dir=sc.nextInt();//dir{1:順時針/2:逆時針}
            int[][] array = new int[n][n];
            int x=0, y=0, state=0, l=0, r=n-1, oldState;
            array[0][0] = current++;
            while(current<=n*n){
                oldState = state;
                switch (state) {
                case 0:
                    if(x==r) state=1;
                    else x++;
                    break;
                case 1:
                    if(y==r){
                        state=2;
                        r--;
                    }else y++;
                    break;
                case 2:
                    if(x==l){
                        state=3;
                        l++;
                    }else x--;
                    break;
                case 3:
                    if(y==l) state=0;
                    else y--;
                    break;
                }
                if(state==oldState){
                    if(dir==1) array[y][x]=current++;
                    else array[x][y]=current++;
                }
            }
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++) System.out.printf("%2d ", array[i][j]);
                System.out.println("");
            }
        }
    }
}
