
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * TEST CLASS FOR SOCKETS
 * @author xfs85
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {

        final String HOST = "127.0.0.1";
        final int PORT = 5000;

        try {
            Socket socket = new Socket(HOST, PORT);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Scanner scanner = new Scanner(System.in);

            
            Thread listenThread = new Thread(() -> {
                try {
                    String messageFromServer;
                    while ((messageFromServer = in.readLine()) != null) {
                        System.out.println("Server: " + messageFromServer);
                    }
                } catch (IOException ex) {
                    System.out.println("Connection closed.");
                }
            });
            listenThread.start();

            
            String messageToSend;
            while (true) {
                System.out.print("You: ");
                messageToSend = scanner.nextLine();
                out.write(messageToSend);
                out.newLine(); 
                out.flush();   

                if (messageToSend.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Disconnected from server.");

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

