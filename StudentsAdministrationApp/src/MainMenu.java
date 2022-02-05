import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    public MainMenu(MainFrame parent) {
        setSize(400,400);
        setLayout(null);


        JTextArea serverResponse = new JTextArea();
        serverResponse.setSize(400, 18);
        serverResponse.setLocation(0, 330);
        add(serverResponse);


        JButton addStudentButton = new JButton("Add student");
        addStudentButton.setSize(260, 30);
        addStudentButton.setLocation(70, 50);

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getAddStudentPage().setVisible(true);
            }
        });
        add(addStudentButton);


        JButton lisStudentButton = new JButton("List students");
        lisStudentButton.setSize(260, 30);
        lisStudentButton.setLocation(70, 100);

        lisStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    parent.getOutputStream().writeObject(new PackageData("list_students", null, null));
                    PackageData response = (PackageData) parent.getInputStream().readObject();

                    parent.getStudentsListPage().fillTable(response.getStudents());
                    parent.getMainMenu().setVisible(false);
                    parent.getStudentsListPage().setVisible(true);
                    serverResponse.setText("Success");
                } catch (Exception f) {
                    serverResponse.setText(f.toString());
                }
            }
        });
        add(lisStudentButton);

        JButton connectButton = new JButton("Change port & IP");
        connectButton.setSize(260, 30);
        connectButton.setLocation(70, 150);

        connectButton.addActionListener(e -> {
            try {
                parent.getSocket().close();
                parent.getMainMenu().setVisible(false);
                parent.getConnectionPage().setVisible(true);
            } catch (Exception exception) {
                serverResponse.setText(exception.toString());
            }
        });
        add(connectButton);


        JButton exitButton = new JButton("Exit");
        exitButton.setSize(260, 30);
        exitButton.setLocation(70, 200);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);
    }
}
