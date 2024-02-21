/* 1117 楊育哲
 * 實作第二題: 二維排序
 */
import java.util.Scanner;
public class h2_1117 {
    public static void twoDimSort(int[][] array){
        for(int i=0; i<array.length-1; i++){
            int minIndex=i;
            for(int j=i+1; j<array.length; j++) if(array[minIndex][0]>array[j][0])minIndex=j;
            int[] temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        for(int i=0; i<array.length-1; i++){
            int subLen=2;
            if(array[i][0]==array[i+1][0]){
                int base = array[i][0];
                while(base==array[i+subLen][0]) subLen++;
                for(int j=i; j<i+subLen; j++){
                    int minIndex=j;
                    for(int k=j+1; k<i+subLen; k++) if(array[k][1]<array[minIndex][1])minIndex=k;
                    int[] temp = array[minIndex];
                    array[minIndex] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int len=sc.nextInt();
        int[][] array = new int[len][2];
        for(int i=0; i<len; i++){
            array[i][0] = sc.nextInt();
            array[i][1] = sc.nextInt();
        }
        twoDimSort(array);
        for(int i=0; i<len; i++){
            for(int j=0; j<array[0].length; j++) System.out.printf("%d ", array[i][j]);
            System.out.println("");
        }
    }
}
