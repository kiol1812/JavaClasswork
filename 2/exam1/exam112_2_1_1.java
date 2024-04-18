/* 41143264 楊育哲 2024/04/18
 * 112學年第二學期期中考1第1題 > 實作運算介面
 */
public class exam112_2_1_1 {
    public static void main(String args[]){
        CCalulator calc = new CCalulator();
        System.out.println(calc.add(2, 3.1));
        System.out.println(calc.sub(2, 3.1));
        System.out.println(calc.mul(2, 3.1));
        System.out.println(calc.div(2, 3.1));
        System.out.println(calc.log(2));
        System.out.println(calc.ln(3.1));
    }
}
interface IBasicCompute{
    public abstract double add(double x, double y);
    public abstract double sub(double x, double y);
    public abstract double mul(double x, double y);
    public abstract double div(double x, double y);
}
interface IAdvCompute{
    public static final double e=2.71828182845905;
    public abstract double log(double x);
    public abstract double ln(double x);
}
interface IFullCompute extends IBasicCompute, IAdvCompute {
}
class CCalulator implements IFullCompute {
    @Override
    public double add(double x, double y) { return x+y; }
    @Override
    public double sub(double x, double y) { return x-y; }
    @Override
    public double mul(double x, double y) { return x*y; }
    @Override
    public double div(double x, double y) { return x/y; }
    @Override
    public double log(double x) {
        return Math.log10(x);
    }
    @Override
    public double ln(double x) {
        return log(x)/log(e);
    }
}