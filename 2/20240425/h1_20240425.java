public class h1_20240425 {    
    public synchronized static void main(String args[]){
        int Arr[] = new int[10000];
        for(int i=0; i<10000; i++){
            Arr[i] = (int)(Math.random()*10000);
        }
        AddUnit AddUnits[] = new AddUnit[10];
        for(int k=0; k<10; k++){
            AddUnits[k] = new AddUnit(Arr, k*1000, (k+1)*1000);
            AddUnits[k].start();
        }
        for(int k=0; k<10; k++){
            try {
                AddUnits[k].join();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        int ans=0;
        for(int i=0; i<10; i++){
            ans+=AddUnits[i].getSum();
        }
        System.out.println(ans);
    }
}
class AddUnit extends Thread{
    private int[] arr;
    private int sum;
    public AddUnit(int Arr[], int start, int end){
        arr = new int[end-start];
        for(int i=start; i<end; i++){
            arr[i-start]=Arr[i];
        }
    }
    @Override
    public void run(){
        int tmp=0;
        for(int i=0; i<arr.length; i++){
            tmp+=arr[i];
        }
        sum=tmp;
    }
    public int getSum(){ return sum; }
}
