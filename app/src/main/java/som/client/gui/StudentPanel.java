package som.client.gui;

import som.client.utils.Utils;
import som.server.database.DaoConstants;
import som.server.database.student.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentPanel extends JScrollPane {
    private List<Student> students = new ArrayList<>();
    private final JPanel dataPanel;

    private static final int NUMBER_OF_FIELDS = 7;

    public StudentPanel(JPanel dataPanel) {
        super(dataPanel);
        this.dataPanel = dataPanel;
        reloadData();
    }

    public void reloadData() {
        students = DaoConstants.STUDENT.getAll();
        buildTableFromData();
    }

    public void buildTableFromData() {
        dataPanel.setLayout(new GridLayout(0, NUMBER_OF_FIELDS));
        createHeader();
        createBody();
    }

    private void createHeader() {
        dataPanel.add(new JLabel("ID", SwingConstants.CENTER));
        dataPanel.add(new JLabel(Utils.toUtf8("Imię"), SwingConstants.CENTER));
        dataPanel.add(new JLabel("Nazwisko", SwingConstants.CENTER));
        dataPanel.add(new JLabel("Dostosowania", SwingConstants.CENTER));
        dataPanel.add(new JLabel("Kod ucznia", SwingConstants.CENTER));
        dataPanel.add(new JLabel("Numer telefonu", SwingConstants.CENTER));
        dataPanel.add(new JLabel("Adres e-mail", SwingConstants.CENTER));
    }

    private void createBody() {
        for (var student : students) {
            createStudentField(student);
        }
    }

    private void createStudentField(Student student) {
        dataPanel.add(new JLabel(Utils.toUtf8(student.pesel()), SwingConstants.CENTER));
        dataPanel.add(new JLabel(Utils.toUtf8(student.name()), SwingConstants.CENTER));
        dataPanel.add(new JLabel(Utils.toUtf8(student.surname()), SwingConstants.CENTER));
        dataPanel.add(new JLabel(Utils.toUtf8(student.requireAdjustments() > 0 ? "•" : ""), SwingConstants.CENTER));
        dataPanel.add(new JLabel(Utils.toUtf8(student.studentCode()), SwingConstants.CENTER));
        dataPanel.add(new JLabel(Utils.toUtf8(student.phoneNumber()), SwingConstants.CENTER));
        dataPanel.add(new JLabel(Utils.toUtf8(student.email()), SwingConstants.CENTER));
    }
}
