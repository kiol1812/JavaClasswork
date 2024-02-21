/* 2024/01/05 楊育哲
 * 實作第二題: 建構式設計樣式練習, 使用B方式(static function)
 */
public class h2_0105_b {
    public static void main(String args[]){
        Taiwan tw = Taiwan.getInstance();
        tw.showInfo();
        Taiwan tw2 = Taiwan.getInstance();
        System.out.println(tw.equals(tw2));
    }
}
class Country{
    String name;
    double population, groundSize, GDP;
    Country(double p, double g, double G){ population=p;groundSize=g;GDP=G; }
    public void showInfo(){ System.out.println(name+"'s population:"+population+"m, groundSize:"+groundSize+"km^2, GDP:"+GDP+"m"); }
}
class Taiwan extends Country{
    private Taiwan(double p, double g, double G){super(p, g, G);}
    private static Taiwan instance;
    public static Taiwan getInstance(){
        if(instance==null){
            instance = new Taiwan(23.26, 36197, 775);
        }
        return instance;
    }
    public void showInfo(){ System.out.println("Taiwan's population:"+population+"m, groundSize:"+groundSize+"km^2, GDP:"+GDP+"m"); }
}
