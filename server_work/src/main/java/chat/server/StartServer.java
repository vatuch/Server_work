package chat.server;

import java.io.IOException;

public class StartServer {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
