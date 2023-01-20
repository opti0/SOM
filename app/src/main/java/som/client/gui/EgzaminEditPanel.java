package som.client.gui;

import javax.swing.*;
import java.awt.*;

public class EgzaminEditPanel extends JPanel {

    JLabel IDL = new JLabel("ID");
    JLabel subjectL = new JLabel("Przedmiot");
    JLabel levelL = new JLabel("Poziom");

    JTextField ID = new JTextField();
    JTextField subject = new JTextField();
    JTextField level = new JTextField();

    public EgzaminEditPanel(){
        setLayout(new GridLayout(3, 2));
        addComponents();
    }

    public void addComponents(){
        add(IDL);
        add(ID);
        add(subjectL);
        add(subject);
        add(levelL);
        add(level);
    }

    public int getIDVal(){
        return Integer.parseInt(ID.getText());
    }

    public int getSubjectVal(){
        return Integer.parseInt(subject.getText());
    }

    public int getLevelVal(){
        return Integer.parseInt(level.getText());
    }
}
