/*
package chat.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteThread implements Runnable {
    private PrintWriter socketWriter;
    private BufferedReader consoleReader;

    public WriteThread(PrintWriter socketWriter, BufferedReader consoleReader) {
        this.socketWriter = socketWriter;
        this.consoleReader = consoleReader;
    }

    public void run() {
        while(true) {
            try {
                String message;
                if ((message = this.consoleReader.readLine()) != null) {
                    this.socketWriter.println(message);
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
