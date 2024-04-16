/* 2024/03/21 楊育哲 41143264
 * 實作介面練習、釐清繼承關係
 */

public class h1_0321 {
    public static void main(String args[]){
    }
}

interface Transport {
    public static final int capacity=10;
    public static final int passenger=0;
    public abstract boolean pickUp(int many);
    public abstract boolean getOff(int many);
}
interface Vehicle { // extends Transport (<-要求, -20)
    public static final int speed=0;
    public static final int wheels=4;
    public abstract void powerUp();
    public abstract void powerOff();
}

class Train implements Transport, Vehicle {
    public static int capacity=100;
    public static int passenger=0;
    public static int speed; //ignore wheel
    @Override
    public void powerUp() {
        this.speed = 10;
    }
    @Override
    public void powerOff() {
        this.speed = 0;
    }
    @Override
    public boolean pickUp(int many) {
        if(passenger+many>capacity){
            passenger=capacity;
            return false;
        }
        passenger+=many;
        return true;
    }
    @Override
    public boolean getOff(int many) {
        if(passenger-many<0){
            return false;
        }
        passenger-=many;
        return true;
    }
}
class Car implements Vehicle {
    public static int capacity=4;
    public static int wheels=4;
    public static int speed;
    @Override
    public void powerUp() {
        this.speed = 5;
    }
    @Override
    public void powerOff() {
        this.speed = 0;
    }
}
class Bike implements Vehicle {
    public static int capacity=1;
    public static int wheels=2;
    public static int speed;
    @Override
    public void powerUp() {
        this.speed=3;
    }
    @Override
    public void powerOff() {
        this.speed=0;
    }
}


