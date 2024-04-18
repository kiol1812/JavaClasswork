/* 41143264 楊育哲 2024/04/18
 * 112學年第二學期期中考1第2題 > 實作例外類別 - 輸入檢查 (!輸出部分改成了英文)
 */

import java.util.Scanner;

public class exam112_2_1_2 {
    public static void main(String args[]){
        CUtil myCUtil = new CUtil();
        int x=0, y=0;
        boolean flag=true;
        Scanner sc = new Scanner(System.in);
        while(flag){
            try{
                System.out.print("please input x: ");
                String x_ = sc.nextLine();//因為讀一行為字串，前面不能有空白
                if(!myCUtil.IntegerCheck(x_)) throw new CMyException(0);
                System.out.print("please input y: ");
                String y_ = sc.nextLine();//因為讀一行為字串，前面不能有空白
                if(!myCUtil.IntegerCheck(y_)) throw new CMyException(0);
                else if(!myCUtil.greateThenZero(y_)) throw new CMyException(1);
                else if(!myCUtil.notZero(x_, y_)) throw new CMyException(2);
                x = Integer.valueOf(x_);
                y = Integer.valueOf(y_);
                flag=false;
            }catch(CMyException ce){
                System.out.println(ce.getMessage());
            }finally{
            }
        }
        System.out.println(x+"^"+y+"="+myCUtil.powerXY(x, y));
    }
}
class CMyException extends Exception {
    private int number;
    public CMyException(int init){
        this.number=init;
    }
    public String getMessage(){
        if(number==0) return "x,y must be integer";
        else if(number==1) return "powers can't less then zero";
        else if(number==2) return "0^0 is undefine";
        return "nukown error";
    }
}
class CUtil{
    public int powerXY(int x, int y){
        int ans=1;
        while(y>0){
            ans*=x;
            y--;
        }
        return ans;
    }
    public boolean greateThenZero(String s){
        if(s.charAt(0)=='-') return false;
        return true;
    }
    public boolean notZero(String s, String s2){
        return (s.charAt(0)!='0'||s2.charAt(0)!='0');
    }
    public boolean IntegerCheck(String s){
        int start=s.charAt(0)=='-'?1:0;
        for(int i=start; i<s.length(); i++){
            int n=s.charAt(i)-'0';
            if(n>=10||n<0) return false;
        }
        return true;
    }
}