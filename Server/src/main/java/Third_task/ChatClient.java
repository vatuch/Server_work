package Third_task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8889;

    public static void main(String[] args) {
        new ChatClient().start();
    }

    public void start() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Connected to server: " + socket);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);

            executorService.execute(new ReadThread(socketReader));
            executorService.execute(new WriteThread(socketWriter, consoleReader));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
