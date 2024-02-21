public class test{
    public static void main(String args[]){
        // int b=10, c=3;
        // System.out.println((double)b/c);
        // System.out.println((double)(b/c));
        // int b=2;
        // b*=3+4;
        // System.out.println(b);
        // int a=5, b=2, c=1;
        // c = (a++)*(++a);
        // a=2;
        // b+=a++;
        // System.out.println(c);
        // System.out.println(b);
        // System.out.println(a);
        long value=1;
        for(int i=0; i<36; i++){
            value<<=1;
            value++;
        }
        int i1=(int)(value & 0x000000fffffff);
        int i2=(int)(value>>28), n=2, add=0;
        // System.out.println(Long.toBinaryString(value));
        // System.out.println(Long.toBinaryString(i2)+" "+Long.toBinaryString(i1));
        System.out.println(value);
        while(i2>0){}
        System.out.println(i1);
    }
}
