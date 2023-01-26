package som.client.gui;

import javax.swing.*;
import java.awt.*;

public class EgzaminEditPanel extends JPanel {
    JLabel subjectL = new JLabel("Przedmiot");
    JLabel levelL = new JLabel("Poziom");
    JTextField subject = new JTextField();
    JTextField level = new JTextField();

    public EgzaminEditPanel(){
        setLayout(new GridLayout(3, 2));
        addComponents();
    }

    public void addComponents(){
        add(subjectL);
        add(subject);
        add(levelL);
        add(level);
    }

    public int getSubjectVal(){
        return Integer.parseInt(subject.getText());
    }

    public int getLevelVal(){
        return Integer.parseInt(level.getText());
    }
}
