import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("已連接伺服器，請輸入訊息");
        new Thread(()->{
            try {
                String line;
                while ((line = userInput.readLine()) != null) {
                    output.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            BufferedReader input;
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String serverInput;
                while ((serverInput = input.readLine()) != null) {
                    System.out.println(serverInput);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}