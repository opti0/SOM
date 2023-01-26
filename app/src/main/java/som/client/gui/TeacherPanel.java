package som.client.gui;

import som.client.utils.Utils;
import som.server.database.DaoConstants;
import som.server.database.teacher.Teacher;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherPanel extends JScrollPane {
    private List<Teacher> teachers = new ArrayList<>();
    private final JPanel dataPanel;

    private static final int NUMBER_OF_FIELDS = 4;

    public TeacherPanel(JPanel dataPanel) {
        super(dataPanel);
        this.dataPanel = dataPanel;
        reloadData();
    }

    public void reloadData() {
        teachers = DaoConstants.TEACHER.getAll();
        buildTableFromData();
    }

    public void buildTableFromData() {
        dataPanel.setLayout(new GridLayout(0, NUMBER_OF_FIELDS));
        createHeader();
        createBody();
    }

    private void createHeader() {
        dataPanel.add(new JLabel(Utils.toUtf8("Imię"), SwingConstants.CENTER));
        dataPanel.add(new JLabel("Nazwisko", SwingConstants.CENTER));
        dataPanel.add(new JLabel("Czy z innej szkoły", SwingConstants.CENTER));
        dataPanel.add(new JLabel("Numer telefonu", SwingConstants.CENTER));
    }

    private void createBody() {
        for (var teacher : teachers) {
            createTeacherField(teacher);
        }
    }

    private void createTeacherField(Teacher teacher) {
        dataPanel.add(new JLabel(Utils.toUtf8(teacher.name()), SwingConstants.CENTER));
        dataPanel.add(new JLabel(Utils.toUtf8(teacher.surname()), SwingConstants.CENTER));
        dataPanel.add(new JLabel(Utils.toUtf8(teacher.fromOtherSchool() > 0 ? "•" : ""), SwingConstants.CENTER));
        dataPanel.add(new JLabel(Utils.toUtf8(teacher.phoneNumber()), SwingConstants.CENTER));
    }
}
