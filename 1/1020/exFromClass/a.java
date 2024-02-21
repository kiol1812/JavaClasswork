/* 10/20 楊育哲
 * 實作99乘法表(奇乘偶...)
 */
public class a {
    public static void main(String args[]){
        for(int i=1; i<10; i++){
            for(int j=2; j<9; j+=2){
                System.out.printf("%d*%d = %4d    ", i, j, i*j);
            }
            System.out.println("");
        }
    }
}
