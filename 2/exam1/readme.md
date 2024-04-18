# 期中考前複習
2024 / 04 / 18

1. 複習類別+instance
```java
class example {
    private example();
    private class Inside{
        private static final example INSTANCE = new example();
    }
    public example getInstance(){
        return Inside.INSTANCE;
    }
}
class example2 {
    private example2();
    private static example instance;
    public example2 getInstance(){
        if(instance==null) instance=new example2;
        return instance;
    }
}
```
2. 棋類遊戲
3. 繼承複習+使用(implements)+介面
```java
interface frame{
    private static final int width=10;
    private static final int height=10;
    public abstract void func();
}
class window{
    private int size;
    public void show(){}
}
class App extends extends implements frame {
    private int width = 100;
    private int height = 100;
    public App(){
        this.size = 100; // may not work, extends only catch public
    }
    @Override
    public void func(){}
}
```
4. 例外處理
```java
public class Main extends Exception {
    public static void main(String args[]) {
        try{
            if(true) throw new caseException(1);
        }catch(caseException ce){
        }finally{
        }
    }
}
class caseException extends Exception {
    private int case_;
    public caseException(int init){
        this.case_ = init;
    }
    public String getMessage(){
        if(case_==0) return "0";
        else if(case==1) return "1";
        else return "2";
    }
}
```
5. 檔案輸入輸出
```java
import java.io.*;
public class Main{
    public static void main(String args[]){
        DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        dout.writeInt(1);
        dout.close();
        DataInputStream din = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        int Int = din.readInt();
        din.close();
    }
}
```