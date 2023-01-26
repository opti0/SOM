package som.client.gui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {

    public Window(int width, int height){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);

        var menuBar = new JMenuBar();
        var fileMenu = new JMenu("Plik");
        var reloadItem = new JMenuItem("Odśwież bazę");
        menuBar.add(fileMenu);
        fileMenu.add(reloadItem);

        List<JPanel> tabs = new ArrayList<>();
        //var s
        StudentsPanel studentsPanel = new StudentsPanel();
        studentsPanel.setName("Uczniowie");
        EditPanel editPanel = new EditPanel();
        editPanel.setName("Edycja");
        var teachersPanel = new TeachersPanel();
        teachersPanel.setName("Nauczyciele");

        var roomsPanel = new RoomsPanel();
        roomsPanel.setName("Sale");
        tabs.add(studentsPanel);
        tabs.add(teachersPanel);
        tabs.add(roomsPanel);
        tabs.add(editPanel);
        add(new TabsPanel(tabs));

        setJMenuBar(menuBar);
        pack();
        setVisible(true);

        //reloadItem.addActionListener(action -> {
         //   studentsPanel.re
        //});
    }
}
