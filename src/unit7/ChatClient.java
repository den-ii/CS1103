package unit7;

import java.io.*;
import java.net.*;

/**
 * ChatClient Class
 * Acts as an interface, receives and sends out messages
 * @author Deni Wisdom Ochiche
 */
public class ChatClient {
    private Socket socket = null;
    private BufferedReader inputConsole = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    /**
     * ChatClient Constructor
     * @param address address of server
     * @param port port server runs on
     */
    public ChatClient(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to the chat server");

            inputConsole = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread sendThread = new Thread(() -> {
                try {
                    String line;
                    while (!(line = inputConsole.readLine()).equals("exit")) {
                        out.println(line);
                    }
                    socket.close();
                    inputConsole.close();
                    out.close();
                } catch (IOException e) {
                    System.out.println("Error in sendThread: " + e.getMessage());
                }
            });

            Thread receiveThread = new Thread(() -> {
                try {
                    String received;
                    while ((received = in.readLine()) != null) {
                        System.out.println(received);
                    }
                } catch (IOException e) {
                    System.out.println("Error in receiveThread: " + e.getMessage());
                }
            });

            sendThread.start();
            receiveThread.start();

            sendThread.join();
            receiveThread.join();

        } catch (UnknownHostException u) {
            System.out.println("Host unknown: " + u.getMessage());
        } catch (IOException i) {
            System.out.println("Unexpected exception: " + i.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }

    /**
     * Entry point to our program
     * @param args cmd-line args
     */
    public static void main(String[] args) {
        ChatClient client = new ChatClient("127.0.0.1", 4440);
    }
}