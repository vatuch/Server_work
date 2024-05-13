package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

    private ExecutorService executorService;

    private static final int PORT = 8889;
    private static List<ClientHandler> clients = new ArrayList<>();
    private ServerSocket serverSocket;

    public ChatServer() {
    }

    public void start() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        System.out.println("Сервер запущен на порту " + PORT);

        this.executorService = Executors.newCachedThreadPool();


        while (true) {
            Socket clientSocket = this.serverSocket.accept();
            System.out.println("Новый клиент подключен: " + clientSocket);
            ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            clients.add(clientHandler);
            //Thread thread = new Thread(clientHandler);
            //thread.start();
            executorService.execute(clientHandler);
        }
    }

    public void broadcastMessage(String message, ClientHandler sender) {
        Iterator<ClientHandler> iterator = clients.iterator();

        while (iterator.hasNext()) {
            ClientHandler client = iterator.next();
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public void stopServer() {
        executorService.shutdown();
    }

}