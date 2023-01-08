package som;

import javax.swing.*;

public class Window extends JFrame {
    private JPanel mainPanel;

    public Window(int width, int height){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);
        add(mainPanel);
    }
}
