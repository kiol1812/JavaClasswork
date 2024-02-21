/* 12/08 楊育哲 
 * 實作第一題: 複數加減乘除
 */
public class h1_1208 {
    static class Complex{
        private float i;
        private float c;
        Complex(float I, float C){i=I;c=C;}
        public Complex add(Complex B){
            return new Complex(i+B.i, c+B.c);
        }
        public Complex sub(Complex B){
            return new Complex(i-B.i, c-B.c);
        }
        public Complex multiply(Complex B){
            return new Complex(i*B.c+B.i*c, c*B.c-i*B.i);
        }
        public Complex div(Complex B){
            Complex t1 = new Complex(-1*B.i, B.c);
            Complex t2 = multiply(t1);
            return new Complex(t2.i/(B.i*B.i+B.c*B.c), t2.c/(B.i*B.i+B.c*B.c));
        }
        public String to_string(){
            return "real: "+c+" + "+"i:"+i;
        }
    }
    static public void main(String args[]){
        Complex A = new Complex(2, 1);//A=1+2i
        Complex B = new Complex(4, 3);//B=3+4i
        System.out.println("A:"+A.to_string());
        System.out.println("B:"+B.to_string());
        System.out.println("A+B:"+(A.add(B)).to_string());
        System.out.println("A-B:"+(A.sub(B)).to_string());
        System.out.println("A*B:"+(A.multiply(B)).to_string());
        System.out.println("A/B:"+(A.div(B)).to_string());
    }
}
