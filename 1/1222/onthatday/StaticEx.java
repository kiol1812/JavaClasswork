/* 12/22 楊育哲
 * 實作上課範例: 先後順序探討
 */
public class StaticEx {
    static int a=2; //(a)

    { System.out.println("non-static block a="+a); a=3; } //(b)

    static{
        System.out.println("static block a="+a); a=4; //(c)
    }

    StaticEx(){ System.out.println("constructor: a="+a); a=5; } //(d)

    static void fun(){ System.out.println("static fun(): a="+a); a=6; } //(e)
    public static void main(String[] args){
        StaticEx.fun();  //(1)
        System.out.println("--------------------");
        (new StaticEx()).fun();  //(2)
    }
}
//(1): a -> c -> e, (2) [承接(1)] -> b -> d -> e
/*
static block a=2
static fun(): a=4
--------------------
non-static block a=6
constructor: a=3
static fun(): a=5
 */