/* 11/03 楊育哲
 * 實作第一題: 1~10000 有多少迴文數字
 */
public class h1_1103 {
    public static void main(String args[]){
        int[] number_ = new int[200]; //9*2+9*10*2=18+180=198<200
        // 1, 11, 101+10, 1001+110
        for(int i=0; i<9; i++) number_[i]=i+1;
        for(int i=1; i<10; i++) number_[i+8]=i*11;
        for(int i=0; i<9; i++){
            for(int j=0; j<10; j++) number_[i*10+j+18]=(i+1)*101+j*10;
        }
        for(int i=0; i<9; i++){
            for(int j=0; j<10; j++) number_[i*10+j+108]=(i+1)*1001+j*110;
        }
        for(int i=0; i<20; i++){
            for(int j=0; j<10; j++) System.out.printf("%4d ", number_[i*10+j]);
            System.out.println("");
        }
    }
}
