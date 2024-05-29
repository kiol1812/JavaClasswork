import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.List;

public class server0523 {
    private Frame frm;
    private TextField txf;
    private TextArea txa;
    private Button sand, start;
    private ServerSocket socket;
    private ClientHandeler client;
    public server0523(){
        frm = new Frame("Client");
        frm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                if(socket!=null&&!socket.isClosed()){
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                System.exit(0);
            }
        });
        frm.setSize(400, 400);
        frm.setLayout(new BorderLayout());
        txf = new TextField();
        txf.setEditable(true);
        txa = new TextArea();
        txa.setEditable(false);
        start= new Button("start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                startServer();
            }
        });
        sand = new Button("Send");
        sand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String msg = txf.getText();
                if(!msg.isEmpty()){
                    client.sendMsg(msg);
                    txa.append("Server: "+msg+"\n");
                    txf.setText("");
                }
            }
        });
        
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(txf, BorderLayout.CENTER);
        panel.add(sand, BorderLayout.EAST);

        frm.add(txa, BorderLayout.CENTER);
        frm.add(panel, BorderLayout.NORTH);
        frm.add(start, BorderLayout.SOUTH);

        frm.setVisible(true);
    }
    public void startServer(){
        new Thread(()->{
            try{
                socket = new ServerSocket(5000);
                txa.append("Server start\n");
                while(true){
                    Socket socket_ = socket.accept();
                    client = new ClientHandeler(socket_);
                    new Thread(client).start();
                }
            }catch(IOException e2){
                e2.printStackTrace();
            }
        }).start();
    }
    
    public static void main(String[] args) {
        server0523 server = new server0523();
        server.demo();
    }
    public void demo(){
        frm.setVisible(true);
    }
    class ClientHandeler implements Runnable {
        private Socket clientSocket;
        private PrintWriter output;
        public ClientHandeler(Socket socket_){
            clientSocket = socket_;
            try{
                output = new PrintWriter(clientSocket.getOutputStream(), true);
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        public void run(){
            try{
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String clientInput;
                while ((clientInput = input.readLine()) != null) {
                    txa.append("client: "+clientInput+"\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        public void sendMsg(String msg){
            output.println("Server: "+msg);
        }
    }
}