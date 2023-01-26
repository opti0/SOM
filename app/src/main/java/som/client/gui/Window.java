package som.client.gui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {

    public Window(int width, int height){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);

        List<JPanel> tabs = new ArrayList<>();
        StudentsPanel studentsPanel = new StudentsPanel();
        studentsPanel.setName("Uczniowie");
        EditPanel editPanel = new EditPanel();
        editPanel.setName("Edycja");
        SettingsPanel settingsPanel = new SettingsPanel();
        settingsPanel.setName("Ustawienia");
        tabs.add(studentsPanel);
        tabs.add(editPanel);
        tabs.add(settingsPanel);
        add(new TabsPanel(tabs));

        pack();
        setVisible(true);
    }
}