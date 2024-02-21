/* 10/13 楊育哲
 * 實作第四題: 同三，但數串type為long
 */
import java.util.Scanner;
public class h4 {
    public static int countLength(long value){
        int count=0;
        while(value>0){
            value/=10;
            count++;
        }
        return count;
    }
    public static int cutLong(long value){//取long個位數
        int b=(int)(value & 0x00000fffffff), f=(int)(value>>28), shouldAdd=0, n=6;//2^28的個位數(26843545'6')
        while(f>0){
            shouldAdd+=n*(f%2);
            n=n*2%10;
            f>>=1;
        }
        return (b%10+shouldAdd%10)%10;
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
        long inputValue=sc.nextLong();
        int lenOfValue=countLength(inputValue);
        long[] intGraph=new long[lenOfValue];
        for(int i=0; i<lenOfValue; i++){
            intGraph[i] = getBitMap(cutLong(inputValue));//陣列為反轉的，最後輸出記得再反轉一次
            inputValue/=10;
        }
        for(int i=lenOfValue-1; i>=0; i--) System.out.println(Long.toBinaryString(intGraph[i]));
        printGraph(intGraph, lenOfValue);
    }
}
