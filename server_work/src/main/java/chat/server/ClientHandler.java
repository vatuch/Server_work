package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private ChatServer server;

    public ClientHandler(Socket socket, ChatServer server) {
        this.clientSocket = socket;
        this.server = server;
    }

    public void run() {
        try {
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = this.in.readLine()) != null) {
                System.out.println("Получено сообщение от клиента: " + inputLine);
                this.server.broadcastMessage(inputLine, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.in.close();
                this.out.close();
                this.clientSocket.close();
                this.server.removeClient(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        this.out.println(message);
    }
}