package som.client.gui;

import som.server.database.Dao;
import som.server.database.exam.Exam;
import som.server.database.exam.ExamParser;
import som.server.database.student.Student;
import som.server.database.student.StudentParser;
import som.server.database.teacher.Teacher;
import som.server.database.teacher.TeacherParser;

import javax.swing.*;
import java.awt.*;

public class EditPanel extends JPanel {

    JButton acceptButton = new JButton();
    EditOptionsComboBox editOptionsComboBox = new EditOptionsComboBox();
    String editOption = editOptionsComboBox.getItemAt(0);
    JPanel insidePanel = new JPanel();

    public EditPanel(){
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(editOptionsComboBox);
        setEditOptionsComboBox();
        add(acceptButton);
        setAcceptButton();
        setEditOptions();

        editOptionsComboBox.addActionListener(e -> {
            editOption = editOptionsComboBox.getSelectedItem().toString();
            setEditOptions();
        });

        acceptButton.addActionListener(e -> {
            switch (editOption){
                case "Uczen" -> addStudentToDB();
                case "Nauczyciel" -> addTeacherToDB();
                case "Egzamin" -> addExamToDB();
            }
        });
    }

    public void addStudentToDB(){
        UczenEditPanel uep = (UczenEditPanel) insidePanel;
        Student debil =  new Student(uep.getPeselVal(), uep.getNameVal(), uep.getSurnameVal(),
                uep.getAdjustmentsVal(), uep.getStudentCodeVal(), uep.getPhoneNumberVal(), uep.getEmailVal());
        Dao dao = new Dao(new StudentParser());
        dao.save(debil);
    }

    public void addTeacherToDB(){
        NauczycielEditPanel nep = (NauczycielEditPanel) insidePanel;
        Teacher teacher = new Teacher(nep.getIDVal(), nep.getNameVal(), nep.getSurnameVal(),
                nep.getFOSVal(), nep.getPhoneNumberVal());
        Dao dao = new Dao(new TeacherParser());
        dao.save(teacher);
    }

    public void addExamToDB(){
        EgzaminEditPanel eep = (EgzaminEditPanel) insidePanel;
        Exam exam = new Exam(eep.getIDVal(), eep.getSubjectVal(), eep.getLevelVal());
        Dao dao = new Dao(new ExamParser());
        dao.save(exam);
    }

    public void setEditOptions(){
        remove(insidePanel);
        repaint();
        switch (editOption){
            case "Nauczyciel" -> insidePanel = new NauczycielEditPanel();
            case "Egzamin" -> insidePanel = new EgzaminEditPanel();
            default -> insidePanel = new UczenEditPanel();
        }
        add(insidePanel);
    }

    public void setAcceptButton(){
        acceptButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        acceptButton.setText("Accept");
    }

    public void setEditOptionsComboBox(){
        editOptionsComboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
    }
}
