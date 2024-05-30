package unit7;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    // List to keep track of all connected clients
    private static HashMap<String, ClientHandler> clients = new HashMap<>();

    private static HashMap<String, Boolean> idsList = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int port = 4440;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.printf("Server started on port %d. Waiting for clients...%n", port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            // Spawn a new thread for each client
            String id = generateIds();
            ClientHandler clientThread = new ClientHandler(clientSocket, clients, id);
            clients.put(id, clientThread);
            new Thread(clientThread).start();
        }
    }

    private static String generateIds(){
        Random random = new Random();
        String id = Integer.toString(random.nextInt(200));
        if (idsList.get(id) != null) generateIds();
        else idsList.put(id, true);
        return id;
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private  HashMap<String, ClientHandler> clients;
    private PrintWriter out;
    private BufferedReader in;

    private  String id;

    public ClientHandler(Socket socket,  HashMap<String, ClientHandler> clients, String id) throws IOException {
        this.clientSocket = socket;
        this.clients = clients;
        this.id = id;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Broadcast message to all clients
                for (var aClient :  clients.entrySet()){
                    if (!aClient.getKey().equals(id)) {
                        aClient.getValue().out.printf(
                                "Message from userId-%s : %s%n",id,inputLine);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}