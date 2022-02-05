import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentsListPage extends JPanel {
    private final JTable studentsTable;

    public StudentsListPage(MainFrame parent) {
        setSize(400,400);
        setLayout(null);

        JLabel ageLabel = new JLabel("STUDENTS LIST");
        ageLabel.setSize(200,30);
        ageLabel.setLocation(130, 0);
        add(ageLabel);


        studentsTable = new JTable();
        studentsTable.setRowHeight(30);
        add(studentsTable);

        JScrollPane scrollPane = new JScrollPane(studentsTable);
        scrollPane.setSize(380,200);
        scrollPane.setLocation(2, 50);
        add(scrollPane);


        JButton backButton = new JButton("Back");
        backButton.setSize(200, 30);
        backButton.setLocation(80, 300);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getStudentsListPage().setVisible(false);
                parent.getMainMenu().setVisible(true);
            }
        });
        add(backButton);
    }

    public void fillTable(ArrayList<Student> students) {
        Object[][] list = new Object[students.size()][4];
        String[] headers = {"ID", "Name", "Surname", "Age"};

        for (int i = 0; i < students.size(); i++) {
            list[i][0] = students.get(i).getId();
            list[i][1] = students.get(i).getName();
            list[i][2] = students.get(i).getSurname();
            list[i][3] = students.get(i).getAge();

            DefaultTableModel model = new DefaultTableModel(list, headers);
            studentsTable.setModel(model);
        }
    }
}
