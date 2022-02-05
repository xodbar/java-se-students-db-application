import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

public class ClientHandler extends Thread {
    private Socket socket;
    private int id;
    private DBManager database;


    public ClientHandler() {}
    public ClientHandler(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
        this.database = new DBManager();
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getConnectionId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public void run() {
        try {
            database.connect();

            ObjectOutputStream outputStream =
                    new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream =
                    new ObjectInputStream(socket.getInputStream());

            PackageData query;
            while ((query = (PackageData) inputStream.readObject()) != null) {
                System.out.println((new Date()) + ": " + id + " queried " + query.getOperationType());

                PackageData response = new PackageData();
                if (query.getOperationType().equalsIgnoreCase("list_students")) {
                    response.setStudents(database.getAllStudents());
                    response.setOperationType("success");
                    response.setStudent(null);

                    outputStream.writeObject(response);
                } else if (query.getOperationType().equalsIgnoreCase("add_student")) {
                    database.addStudent(query.getStudent());

                    response.setOperationType("success");
                    response.setStudents(null);
                    response.setStudent(null);

                    outputStream.writeObject(response);
                } else {
                    response.setOperationType("error");
                    response.setStudents(null);
                    response.setStudent(null);

                    outputStream.writeObject(response);
                }
            }
        } catch (SocketException e) {
            System.out.println("Client " + " id has disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
