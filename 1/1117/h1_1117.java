/* 1117 楊育哲
 * 實作第一題: 找第101個emirp
 */
public class h1_1117 {
    public static boolean isPrime(int value){
        if(value!=2&&value%2==0) return false;
        for(int i=3; i<value; i+=2){
            if(value%i==0) return false;
        }
        return true;
    }
    public static int reverse(int value){
        int ans=0;
        while(value>0){
            ans = ans*10+value%10;
            value/=10;
        }
        return ans;
    }
    public static void main(String args[]){
        int current=1, finded=1;//已加入2
        if(isPrime(2)&&isPrime(reverse(2))) System.out.printf("%d\n", 2);
        while(finded<101){
            current+=2;//3、5、7、9、..., 跳過二，於前面補
            if(isPrime(current)){
                if(isPrime(reverse(current))){
                    finded++;   
                    System.out.printf("%d\n", current);
                }
            }
        }
        System.out.printf("emirp 101: %d\n", current);//emirp 101
    }
}
