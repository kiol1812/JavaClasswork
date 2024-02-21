/* 
 * 
 */
public class onClassEx {
   public static void main(String args[]){
        Circle father = new Coin();
        System.out.println(father.getClass()); //class Coin, 為子物件
        Coin f = new Coin();
        System.out.println(f.equals(father));
   }
}
class Circle{}
class Coin extends Circle{}

class EagerInitalization{
    private static final EagerInitalization instance = new EagerInitalization();
    private EagerInitalization(){}// 寫這行的目的為: 使外部看不到建構函數，不寫的話，java編譯會自動加一個
    public static EagerInitalization getInstance(){ return instance; }
}

class StaticBlockSingleton{
    private static StaticBlockSingleton instance;
    private StaticBlockSingleton(){}
    static{
        try{
            instance = new StaticBlockSingleton();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    public static StaticBlockSingleton getInstance(){ return instance; }
}

class LazyInitializedSingleton{
    private static LazyInitializedSingleton instance;
    private LazyInitializedSingleton(){}
    public static LazyInitializedSingleton getInstance(){
        if(instance==null){
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
}
class BillPughSingleton{
    private BillPughSingleton(){}
    private static class SingletonHelper{//return時才呼叫建立
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    public static BillPughSingleton getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
