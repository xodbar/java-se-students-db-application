import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8115);

            int id = 0;
            while (id < 100) {
                Socket client = serverSocket.accept();
                id++;

                System.out.println((new Date()) + ": New client #" + id + " has connected.");
                ClientHandler clientHandler = new ClientHandler(client, id);
                clientHandler.start();
            }
        } catch (SocketException e) {
            System.out.println((new Date()) + ": Client has disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
