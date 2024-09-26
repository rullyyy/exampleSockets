
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * TEST CLASS FOR SOCKETS
 * @author xfs85
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = null;
        final int PORT = 5000;

        server = new ServerSocket(PORT);
        System.out.println("Server On");

        while (true) {
           Socket socket = server.accept();
            System.out.println("Client has joined");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

           
            Thread clientHandler = new Thread(() -> {
                try {
                    String messageFromClient;
                    while ((messageFromClient = in.readLine()) != null) {
                        System.out.println("Client: " + messageFromClient);
                        
                        out.newLine();
                        out.flush();  

                        if (messageFromClient.equalsIgnoreCase("exit")) {
                            break;
                        }
                    }
                    socket.close();
                    System.out.println("Client Disconnected");
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            clientHandler.start();
        }
    }
}



