import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public  class h2_server_20240425 {
    public synchronized static void main(){
        try{
            ServerSocket svs = new ServerSocket(2525);
            System.out.println("wait client response");
            // Socket s=svs.accept();
            // System.out.println("connected");
            // OutputStream out = s.getOutputStream();
            ClientHandler threads[] = new ClientHandler[10];
            int clients=0;
            while(true){
                Socket s = svs.accept();
                threads[clients] = new ClientHandler(s);
                threads[clients++].start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

class ClientHandler extends Thread {
    private Socket s;
    public ClientHandler(Socket socket){
        s = socket;
    }
    @Override
    public void run(){
        byte buff[] = new byte[1024];
        try{
            while(true){
                InputStream in = s.getInputStream();
                int n=in.read(buff);
                System.out.print("Received from sercer: ");
                System.out.println(new String(buff, 0, n));
                in.close();
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
}