import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionPage extends JPanel {
    public ConnectionPage(MainFrame parent) {
        setSize(400,400);
        setLayout(null);

        JLabel ipLabel = new JLabel("Enter IP:");
        ipLabel.setSize(200,30);
        ipLabel.setLocation(75, 100);
        add(ipLabel);

        JTextField ipField = new JTextField("localhost");
        ipField.setSize(200, 30);
        ipField.setLocation(130, 100);
        add(ipField);


        JLabel portLabel = new JLabel("Enter port:");
        portLabel.setSize(200,30);
        portLabel.setLocation(75, 150);
        add(portLabel);

        JTextField portField = new JTextField();
        portField.setSize(190, 30);
        portField.setLocation(140, 150);
        add(portField);


        JTextArea serverResponse = new JTextArea();
        serverResponse.setSize(260, 30);
        serverResponse.setLocation(70, 250);
        add(serverResponse);


        JButton connectButton = new JButton("Connect");
        connectButton.setSize(260, 30);
        connectButton.setLocation(70, 200);

        connectButton.addActionListener(e -> {
            try {
                parent.setSocket(new Socket(ipField.getText(), Integer.parseInt(portField.getText())));
                serverResponse.setText("success");

                parent.setInputStream(new ObjectInputStream(parent.getSocket().getInputStream()));
                parent.setOutputStream(new ObjectOutputStream(parent.getSocket().getOutputStream()));

                parent.getConnectionPage().setVisible(false);
                parent.getMainMenu().setVisible(true);
            } catch (Exception exception) {
                serverResponse.setText(exception.toString());
            }
        });
        add(connectButton);
    }
}
