/* 10/20 楊育哲
 * 實作第一題: 轉換輸入值n的base，從base r to k
 * 1<r, k<=16
 * note: 需要檢查n在base r合不合理
 */

import java.util.HashMap;
import java.util.Scanner;

public class h1_1020_w{
    public static void main(String args[]){
        int r=0, k=0, dexN=0;
        String n;
        Scanner sc = new Scanner(System.in);
        HashMap<Character, Integer> m = new HashMap<Character, Integer>();
        for(int i=0; i<10; i++) m.put((char)(i+'0'), i);
        for(int i=0; i<6; i++) m.put((char)(i+'a'), i+10);
        HashMap<Integer, Character> mr = new HashMap<Integer, Character>();
        for(int i=0; i<10; i++) mr.put(i, (char)(i+'0'));
        for(int i=0; i<6; i++) mr.put(i+10, (char)(i+'a'));
        System.out.print("請輸入一個字串(n): ");
        n = sc.nextLine();
        System.out.print("請輸入兩個數字(r, k): ");
        r = sc.nextInt();
        k = sc.nextInt();
        String kBaseN="", temp="";
        boolean check=true;
        for(int index=n.length()-1, power=0; index>=0; index--, power++){
            if(m.get(n.charAt(index))>=r){
                check=false;
                break;
            }
            dexN+=m.get(n.charAt(index))*Math.pow(r, power);
        }
        if(check){
            while(dexN>0){
                temp+=mr.get(dexN%k);
                dexN/=k;
            }
            for(int i=temp.length()-1; i>=0; i--){
                kBaseN+=temp.charAt(i);
            }
            System.out.println(kBaseN);
        }else System.out.println("輸入有錯誤");
    }
}