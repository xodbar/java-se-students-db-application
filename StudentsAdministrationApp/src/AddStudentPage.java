import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentPage extends JPanel {
    public AddStudentPage(MainFrame parent) {
        Integer[] ages = new Integer[100];
        for(int i =0;i<100;i++){
            ages[i]= i;
        }

        setSize(400,400);
        setLayout(null);

        JLabel nameLabel = new JLabel("Enter name:");
        nameLabel.setSize(200,30);
        nameLabel.setLocation(30, 30);
        add(nameLabel);

        JTextField nameField = new JTextField("John");
        nameField.setSize(200, 30);
        nameField.setLocation(130, 30);
        add(nameField);


        JLabel surnameLabel = new JLabel("Enter surname:");
        surnameLabel.setSize(200,30);
        surnameLabel.setLocation(30, 80);
        add(surnameLabel);

        JTextField surnameField = new JTextField("Smith");
        surnameField.setSize(200, 30);
        surnameField.setLocation(130, 80);
        add(surnameField);


        JLabel ageLabel = new JLabel("Enter age:");
        ageLabel.setSize(200,30);
        ageLabel.setLocation(30, 130);
        add(ageLabel);

        JComboBox agesBox = new JComboBox(ages);
        agesBox.setSize(200, 30);
        agesBox.setLocation(130, 130);
        add(agesBox);


        JTextArea serverResponse = new JTextArea();
        serverResponse.setSize(300, 100);
        serverResponse.setLocation(30, 250);
        add(serverResponse);


        JButton addButton = new JButton("Add");
        addButton.setSize(150, 30);
        addButton.setLocation(30, 180);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Student newStudent = new Student();
                    newStudent.setId(null);
                    newStudent.setName(nameField.getText());
                    newStudent.setSurname(surnameField.getText());
                    newStudent.setAge((Integer) agesBox.getSelectedItem());

                    PackageData pd = new PackageData("add_student", null, newStudent);
                    parent.getOutputStream().writeObject(pd);

                    if ((pd = (PackageData) parent.getInputStream().readObject()) != null)
                        serverResponse.setText(pd.getOperationType());

                } catch (Exception exception) {
                    serverResponse.setText(exception.toString());
                }
            }
        });
        add(addButton);


        JButton backButton = new JButton("Back");
        backButton.setSize(150, 30);
        backButton.setLocation(180, 180);

        backButton.addActionListener(e -> {
           parent.getAddStudentPage() .setVisible(false);
           parent.getMainMenu().setVisible(true);
        });
        add(backButton);
    }
}
