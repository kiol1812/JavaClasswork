## Thread

```java
// use extends Thread
public class Main{
    public static void main(String args[]){
        Ctext dog = new Ctext("dog");
        Ctext cat = new Ctext("cat");
        dog.start();
        cat.start();
    }
}
class Ctext extends Thread {
    private String id;
    public Ctext(String str){
        id=str;
    }
    @Override
    public void run(){
        for(int i=0; i<4; i++){
            for(int j=0; j<10000; j++);
            System.out.println(id);
        }
    }
}
```

```java
//use implements Runnable
public class Main{
    public static void main(String args[]){
        Ctext dog = new Ctext("dog");
        Ctext cat = new Ctext("cat");
        Thread t1 = new Thread(dog);
        Thread t2 = new Thread(cat);
        t1.start();
        t2.start();
    }
}
class Ctext implements Runnable {
    private String id;
    public Ctext(String str){
        id=str;
    }
    public void run(){
        for(int i=0; i<4; i++){
            for(int j=0; j<10000; j++);
            System.out.println(id);
        }
    }
}
```
```java
//sleep
public class Main{
    public static void main(String args[]){
        Ctext dog = new Ctext("dog");
        Ctext cat = new Ctext("cat");
        dog.start();
        cat.start();
        System.out.println("main() finished");
    }
}
class Ctext extends Thread {
    private String id;
    public Ctext(String str){
        id=str;
    }
    public void run(){
        try{
            sleep((int)(1000*Math.random()));
        }catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println(id);
    }
}
```
```java
//synchronized 參考作業一
```

## server & client


