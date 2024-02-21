/* 12/22 楊育哲
 * 實作第一題: 父類別繼承練習
 */
public class p1_1222 {
    public static void main(String args[]){
        Laptop A = new Laptop("HP", 10000, 11);
        Laptop C = new Laptop("A", 20000, 13);
        A.onBot(); C.onBot();
        A.info(); C.info();

        SmartPhone B = new SmartPhone("123546789", "apple", 10000, 7); B.onBot();
        System.out.print("call test: "); B.call("987654321");
        B.offBot();
        System.out.print("call test: "); B.call("987654321");
    }
}
class Device{
    private String brand;
    private int size, price;
    private boolean state;
    Device(String name, int s, int p){state=false;brand=name;size=s;price=p;}
    public void onBot(){state=true;}
    public void offBot(){state=false;}
    public boolean Bot(){return state;}
    public void info(){
        System.out.println("brand: "+brand);
        System.out.println("size: "+size);
        System.out.println("price: "+price);
    }
}
class Laptop extends Device{
    private static int power, voice;
    Laptop(String name, int s, int p){super(name, s, p); power=100; voice=0;}
    public void setVoice(int v){if(v>=0&&v<=100)voice=v;}
    public void checkPower(){System.out.println("power :"+power+"%");}
}
class SmartPhone extends Device{
    private static String number;
    SmartPhone(String no, String name, int s, int p){super(name, s, p); number=no;}
    public void call(String no){if(Bot())System.out.println("call "+no+" from "+number);else System.out.println("phone off.");}
}
