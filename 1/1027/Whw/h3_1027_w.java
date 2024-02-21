/* 1027 楊育哲 周作業
 * 實作第三題: 以spiral order印出陣列內容，陣列由使用者輸入，其為任意形狀
 * matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 */
import java.util.Scanner;
public class h3_1027_w {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("輸入符合規定的二維陣列(陣列區只包含'['、']'、','、數字, 且得每排、排列個數一樣):");
        String s = sc.nextLine();
        int index=0;
        while(s.charAt(index)!='[') index++;
        int current=index+1, r=0, c=1;
        while(current<s.length())
            if(s.charAt(current++)=='[') r++;
        current=index;
        while(s.charAt(current)!=']')
            if(s.charAt(current++)==',') c++;
        int[][] arr=new int[r][c];
        current=index+2;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                int num=0;
                while(current<s.length()&&s.charAt(current)!=','&&s.charAt(current)!=']'){
                    num = num*10+(int)(s.charAt(current++)-'0');
                }
                arr[i][j] = num;
                current+=1;
            }
            current+=2;
        }
        current = 0;
        int end=r*c, ceil=0;
        int[] cood={0, 0}, move={0, 0};
        int state=0;//0:r,右至左 | 1:c,上至下 | 2:r,左至右 | 3:c,下至上
        while(current<end){
            index = 0;
            ceil = (state%2==1)? r:c;
            move[0] = (state%2==1)? -1*(state-2):0;
            move[1] = (state%2==1)? 0:-1*(state-1);
            while(index<ceil-1){
                System.out.printf("%3d ", arr[cood[0]][cood[1]]);
                cood[0]+=move[0];
                cood[1]+=move[1];
                current++;
                index++;
            }
            state = (state+1)%4;
            if(state==3){
                r--;
                c--;
            }
        }
    }
}
