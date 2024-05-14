import java.util.Scanner;

public class test_ {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int size; size = sc.nextInt();
        int arr[] = new int[size];
        for(int i=0; i<size; i++) arr[i]=sc.nextInt();
        int i=1, j=0;
        while(i<size){
            j=i;
            while(j>0 && arr[j]<arr[j-1]){
                int tmp=arr[j];
                arr[j]=arr[j-1];
                arr[j-1]=tmp;
                j--;
            }
            i++;
        }
        for(int k=0; k<size; k++){
            System.out.print(arr[k]+" ");
        }
    }
}
