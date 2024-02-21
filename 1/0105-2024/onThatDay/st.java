/* 2024/01/05 楊育哲
 * 實作第一題: 順序探討
 */
public class st extends t{
    int c=2;//6
    static { System.out.println(a);a=4; }//7
    st(){ System.out.println(a+" "+c);a=5; }//8
    { System.out.println(a+" "+c);c=3; }//9
    public static void main(String args[]){
        st b=new st();//10
    }
}
class t{
    static int a=1;//1
    int b=0;//2
    static { System.out.println(a);a=2; }//3
    t(){ System.out.println(a+" "+b);a=3; }//4
    { System.out.println(a+" "+b);b=2; }//5
}
// 10, 1, 3, 7, 2, 5, 4, 6, 9, 8