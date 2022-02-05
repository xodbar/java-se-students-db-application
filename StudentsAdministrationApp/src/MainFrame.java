import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainFrame extends JFrame {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private final ConnectionPage connectionPage;
    private final MainMenu mainMenu;
    private final AddStudentPage addStudentPage;
    private final StudentsListPage studentsListPage;


    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Mini Project BITLAB");
        setSize(400,400);
        setLayout(null);

        connectionPage = new ConnectionPage(this);
        add(connectionPage);

        mainMenu = new MainMenu(this);
        add(mainMenu);

        addStudentPage = new AddStudentPage(this);
        add(addStudentPage);

        studentsListPage = new StudentsListPage(this);
        add(studentsListPage);
    }


    public ConnectionPage getConnectionPage() {
        return connectionPage;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public AddStudentPage getAddStudentPage() {
        return addStudentPage;
    }

    public StudentsListPage getStudentsListPage() {
        return studentsListPage;
    }
}
