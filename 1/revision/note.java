package revision; //?
/* 2024/1/11 楊育哲
 * 第一學期總複習(2023/11/17~2024/01/05)
 * 目錄(認為的考試重點 / 常用備忘): 
 *      1. scanner: import java.util.Scanner; Scanner sc = new Scanner(System.in);
 *      2. random:  Maht.random()*range; //0~range(不含range: ex. *10: 0~9.99..); //(int)取下限,如(int)(9.99)=9
 *      3. 先後順序探討: 父static -> 父stacic-block -> 子static -> 子static-block -> 父non-static -> 父non-static-block ->
 *          父建構子 -> 子non-static -> 子non-static-block -> 子建構子
 *      4. class methom: getclass() / equals() / toString()
 *      5. 建構式設計樣式: 唯一class物件(只有一個記憶體存), 參考java_note.txt
 */
import java.util.Scanner;
public class note {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        double test = Math.random()*10;
    }
}
