package som.client.gui;

import javax.swing.*;
import java.awt.*;

public class TeachersPanel extends JPanel {
    public TeachersPanel(){
        add(new TeacherPanel(new JPanel()), BorderLayout.CENTER);
    }
}
