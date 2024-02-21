/* 10/13 楊育哲
 * 實作第三題: 點陣圖表示數串
 */
import java.util.Scanner;
public class h3_1020_w {
    public static int countLength(int value){
        int count=0;
        while(value>0){
            value/=10;
            count++;
        }
        return count;
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
        long[] bitMap={
            0b000000011110010010010010011110000000,
            0b000000011100001000001000001100001000,
            0b000000011110000010011110010000011110,
            0b000000011110010000011110010000011110,
            0b000000010000010000011110010010010010,
            0b000000011110010000011110000010011110,
            0b000000011110010010011110000010000010,
            0b000000010000010000010000010000011110,
            0b000000011110010010011110010010011110,
            0b000000010000011110010010010010011110
        };
        Scanner sc = new Scanner(System.in);
        System.out.println("輸入一串數字: ");
        int inputValue=sc.nextInt();
        int lenOfValue=countLength(inputValue);
        long[] intGraph=new long[lenOfValue];
        for(int i=0; i<lenOfValue; i++){
            intGraph[i] = bitMap[inputValue%10];//陣列為反轉的，最後輸出記得再反轉一次
            inputValue/=10;
        }
        // for(int i=lenOfValue-1; i>=0; i--) System.out.println(Long.toBinaryString(intGraph[i]));
        printGraph(intGraph, lenOfValue);
    }
}
