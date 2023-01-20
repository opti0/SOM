package som.client.gui;

import javax.swing.*;
import java.awt.*;

public class StudentsPanel extends JPanel {
    public StudentsPanel(){
        add(new StudentPanel(new JPanel()), BorderLayout.CENTER);
    }
}
