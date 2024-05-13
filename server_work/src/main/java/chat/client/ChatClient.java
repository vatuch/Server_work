package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8889;

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ChatClient() {
        try {this.clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);

            System.out.println("Подключение к серверу " + SERVER_HOST + ":" + SERVER_PORT);

            Thread receiveThread = new Thread(new ReceiveThread());
            receiveThread.start();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReceiveThread implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}
