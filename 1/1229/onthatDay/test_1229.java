/* 12/29 楊育哲
 * 多型練習
 */
public class test_1229 {
    public static void main(String args[]){
    }
}
class Circle{
    double r;
    public void view(){ System.out.println("flag1"); }
}
class Coin extends Circle{
    int value;
    public void view(){ System.out.println("flag1"); } //override
}
