/*
package chat.client;

import java.io.BufferedReader;
import java.io.IOException;


public class ReadThread implements Runnable {
    private BufferedReader socketReader;

    public ReadThread(BufferedReader socketReader) {
        this.socketReader = socketReader;
    }

    public void run() {
        while(true) {
            try {
                String message;
                if ((message = this.socketReader.readLine()) != null) {
                    System.out.println("Received message: " + message);
                    continue;
                }
            } catch (IOException var2) {
                IOException e = var2;
                e.printStackTrace();
            }

            return;
        }
    }
}

*/
