/* 10/20 楊育哲
 * 實作第二題: 車票價計算+顯示
 */

import java.util.HashMap;

public class h2_1020_w {
    public static void main(String args[]){
        String[] site={"台北", "桃園", "新竹", "台中", "嘉義", "台南", "高雄"};
        HashMap<String, Integer> m = new HashMap<String, Integer>();
        m.put("台北", 25);
        m.put("桃園", 49);
        m.put("新竹", 95);
        m.put("台中", 178);
        m.put("嘉義", 264);
        m.put("台南", 327);
        m.put("高雄", 373);
        System.out.print("里程表  ");
        for(int i=0; i<7; i++) System.out.printf("%2s  ", site[i]);
        System.out.println("");
        for(int i=0; i<7; i++){
            System.out.printf("%2s    ", site[i]);
            for(int j=0; j<7; j++){
                System.out.printf("%4d  " ,Math.abs(m.get(site[j])-m.get(site[i])));
            }
            System.out.println("");
        }
        System.out.print("票價表  ");
        for(int i=0; i<7; i++) System.out.printf("%2s  ", site[i]);
        System.out.println("");
        for(int i=0; i<7; i++){
            System.out.printf("%2s    ", site[i]);
            for(int j=0; j<7; j++){
                if(Math.abs(m.get(site[j])-m.get(site[i]))>200){
                    System.out.printf("%4d  " ,2*Math.abs(m.get(site[j])-m.get(site[i])));
                }else if(Math.abs(m.get(site[j])-m.get(site[i]))>50){
                    System.out.printf("%4.0f  " ,2.2*Math.abs(m.get(site[j])-m.get(site[i])));
                }else{
                    System.out.printf("%4.0f  " ,2.5*Math.abs(m.get(site[j])-m.get(site[i])));
                }
            }
            System.out.println("");
        }
    }
}
