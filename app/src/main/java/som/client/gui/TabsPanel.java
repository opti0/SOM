package som.client.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TabsPanel extends JTabbedPane {

    public TabsPanel(List<JPanel> panels){
        super(JTabbedPane.TOP);

        int i = 0;
        for(JPanel panel : panels){
            add(panel);
            setTitleAt(i, panel.getName());
            setFont(new Font("Calibri", Font.PLAIN, 14));
            i++;
        }
    }
}
