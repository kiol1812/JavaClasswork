/* 10/13 楊育哲
 * 實作第三題: 點陣圖表示數串
 */
import java.util.Scanner;
public class h3 {
    public static int countLength(int value){
        int count=0;
        while(value>0){
            value/=10;
            count++;
        }
        return count;
    }
    public static long getBitMap(int int_){
        int[][] BITMAP={
            {
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0
            },
            {
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0
            },
            {
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0
            },
            {
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0
            },
            {
                0, 1, 0, 0, 1, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 0
            },
            {
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0
            },
            {
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0
            },
            {
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 0
            },
            {
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0
            },
            {
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 0
            }
        };
        long bitMap=0;
        for(int i=35; i>=0; i--){
            bitMap+=BITMAP[int_][i];
            bitMap<<=1;
        }
        bitMap>>=1;
        return bitMap;
    }
    public static void printGraph(long[] intGraph, int len){
        for(int k=0; k<6; k++){
            for(int i=len-1; i>=0; i--){
                for(int j=0; j<6; j++){
                    // System.out.print(Math.abs((int)intGraph[i]%2));
                    if(Math.abs((int)intGraph[i]%2)>0) System.out.print("1");
                    else System.out.print(" ");
                    intGraph[i]/=2;
                }
            }
            System.out.println("");
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("輸入一串數字: ");
        int inputValue=sc.nextInt();
        int lenOfValue=countLength(inputValue);
        long[] intGraph=new long[lenOfValue];
        for(int i=0; i<lenOfValue; i++){
            intGraph[i] = getBitMap(inputValue%10);//陣列為反轉的，最後輸出記得再反轉一次
            inputValue/=10;
        }
        for(int i=lenOfValue-1; i>=0; i--) System.out.println(Long.toBinaryString(intGraph[i]));
        printGraph(intGraph, lenOfValue);
    }
}
