/* 12/29 楊育哲
*  多型練習
*/
public class p1_1229 {
    public static void main(String args[]){
        Country unknow = new Country(0, 0, 0);
        unknow.showInfo();
        Taiwan Tw = new Taiwan(23.26, 36197, 775);
        Tw.showInfo();
        German Gm = new German(84.4, 348672, 4072);
        Gm.showInfo();
        England Eg = new England(67.79, 241930, 3071);
        Eg.showInfo();
        Japan Jp = new Japan(124.95, 364485, 4231);
        Jp.showInfo();
        Korea Kr = new Korea(51.69, 100210, 1665);
        Kr.showInfo();
        America Am = new America(334.23, 9147593, 25463);
        Am.showInfo();
        France Fr = new France(67.84, 640427, 2783);
        Fr.showInfo();
    }
}
class Country{
    double population, groundSize, GDP;
    Country(double p, double g, double G){ population=p;groundSize=g;GDP=G; }
    public void showInfo(){ System.out.println("this country's population:"+population+"m, groundSize:"+groundSize+"km^2, GDP:"+GDP+"m"); }
}
class Taiwan extends Country{
    Taiwan(double p, double g, double G){ super(p, g, G); }
    public void showInfo(){ System.out.println("Taiwan's population:"+population+"m, groundSize:"+groundSize+"km^2, GDP:"+GDP+"m"); }
}
class German extends Country{
    German(double p, double g, double G){ super(p, g, G); }
    public void showInfo(){ System.out.println("German's population:"+population+"m, groundSize:"+groundSize+"km^2, GDP:"+GDP+"m"); }
}
class England extends Country{
    England(double p, double g, double G){ super(p, g, G); }
    public void showInfo(){ System.out.println("England's population:"+population+"m, groundSize:"+groundSize+"km^2, GDP:"+GDP+"m"); }
}
class Japan extends Country{
    Japan(double p, double g, double G){ super(p, g, G); }
    public void showInfo(){ System.out.println("Japan's population:"+population+"m, groundSize:"+groundSize+"km^2, GDP:"+GDP+"m"); }
}
class Korea extends Country{
    Korea(double p, double g, double G){ super(p, g, G); }
    public void showInfo(){ System.out.println("Korea's population:"+population+"m, groundSize:"+groundSize+"km^2, GDP:"+GDP+"m"); }
}
class America extends Country{
    America(double p, double g, double G){ super(p, g, G); }
    public void showInfo(){ System.out.println("America's population:"+population+"m, groundSize:"+groundSize+"km^2, GDP:"+GDP+"m"); }
}
class France extends Country{
    France(double p, double g, double G){ super(p, g, G); }
    public void showInfo(){ System.out.println("France's population:"+population+"m, groundSize:"+groundSize+"km^2, GDP:"+GDP+"m"); }
}
