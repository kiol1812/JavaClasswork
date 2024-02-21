/* 12/29 楊育哲
 * 實作第二題: 先後順序探討
 */
public class sub_t extends t{
    static { a=4;System.out.println(a); } // 4
    sub_t(){ a=5;System.out.println(a); } // 5
    public static void main(String args[]){
        sub_t b = new sub_t(); //6
    }
}
class t{
    static int a=1;// 1
    static { System.out.println(a);a=2;System.out.println(a); }// 2
    t(){ a=3;System.out.println(a); }    // 3
}