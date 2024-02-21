// import java.util.concurrent.TimeUnit;
import java.util.Scanner;
public class test {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("|");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // TimeUnit.SECONDS.sleep(1);
            System.out.print("\\");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // TimeUnit.SECONDS.sleep(1);
            System.out.print("-");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // TimeUnit.SECONDS.sleep(1);
            System.out.print("/");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // TimeUnit.SECONDS.sleep(1);
            System.out.print("-");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // TimeUnit.SECONDS.sleep(1);
            System.out.print("\\");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // TimeUnit.SECONDS.sleep(1);
        }
    }
}
