package som.client.gui;

import som.client.gui.StudentPanel;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JPanel mainPanel = new JPanel();

    public Window(int width, int height){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        add(new StudentPanel(mainPanel), BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
}
