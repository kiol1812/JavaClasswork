import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class h2_client_20240425 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("try to connect.");
            Socket s=new Socket("127.0.0.1", 2525);
            System.out.println("connected");
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String message;
            while((message=input.readLine())!=null){
            }
            s.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
