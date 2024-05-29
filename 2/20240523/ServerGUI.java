import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;

public class ServerGUI {
    private Frame mainFrame;
    private TextArea messageArea;
    private TextField messageInput;
    private Button startButton;
    private Button sendButton;
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    public ServerGUI() {
        clients = new ArrayList<>();
        prepareGUI();
    }

    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        serverGUI.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new Frame("Server Application");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new BorderLayout());

        messageArea = new TextArea();
        messageArea.setEditable(false);

        messageInput = new TextField();
        messageInput.setEditable(true);

        startButton = new Button("Start Server");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });

        sendButton = new Button("Send Message");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = messageInput.getText();
                if (!message.isEmpty()) {
                    broadcastMessage("Server: " + message);
                    appendMessage("Server: " + message);
                    messageInput.setText("");
                }
            }
        });

        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(messageInput, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        mainFrame.add(messageArea, BorderLayout.CENTER);
        mainFrame.add(panel, BorderLayout.NORTH);
        mainFrame.add(startButton, BorderLayout.SOUTH);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    if (serverSocket != null && !serverSocket.isClosed()) {
                        serverSocket.close();
                    }
                    for (ClientHandler client : clients) {
                        client.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }

    private void showEventDemo() {
        mainFrame.setVisible(true);
    }

    private void startServer() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(5000);
                appendMessage("Server start");
                while (true) {
                    Socket socket = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(socket);
                    clients.add(clientHandler);
                    new Thread(clientHandler).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
                appendMessage("Server error: " + e.getMessage());
            }
        }).start();
    }

    private void appendMessage(String message) {
        messageArea.append(message + "\n");
    }

    private void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter output;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                this.output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientInput;
                while ((clientInput = input.readLine()) != null) {
                    appendMessage(clientInput);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        public void sendMessage(String message) {
            if (output != null) {
                output.println(message);
            }
        }

        public void close() {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
                clients.remove(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}