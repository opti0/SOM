package som.client.gui;

import com.formdev.flatlaf.FlatLightLaf;
import som.client.csv.CsvParser;
import som.client.utils.Utils;
import som.server.database.DaoConstants;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {

    public Window(int width, int height) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);

        var menuBar = new JMenuBar();
        var dbMenu = new JMenu("Baza");
        var reloadItem = new JMenuItem(Utils.toUtf8("Odśwież bazę"));
        menuBar.add(dbMenu);
        dbMenu.add(reloadItem);

        var csvItem = new JMenuItem(Utils.toUtf8("Import z pliku CSV"));
        dbMenu.add(csvItem);

        List<JPanel> tabs = new ArrayList<>();
        var studentsPanel = new JPanel();
        var studentDataPanel = new StudentPanel(studentsPanel);
        studentsPanel.setName(Utils.toUtf8("Uczniowie"));

        var roomsPanel = new JPanel();
        var roomDataPanel = new RoomPanel(roomsPanel);
        roomsPanel.setName("Sale");

        var teachersPanel = new JPanel();
        var teacherDataPanel = new TeacherPanel(teachersPanel);
        teachersPanel.setName("Nauczyciele");

        EditPanel editPanel = new EditPanel();
        editPanel.setName("Edycja");

        SettingsPanel settingsPanel = new SettingsPanel();
        settingsPanel.setName("Ustawienia");

        tabs.add(studentsPanel);
        tabs.add(teachersPanel);
        tabs.add(roomsPanel);
        tabs.add(editPanel);
        tabs.add(settingsPanel);
        add(new TabsPanel(tabs));

        setJMenuBar(menuBar);
        pack();
        setVisible(true);

        reloadItem.addActionListener(action -> {
           studentDataPanel.reloadData();
           teacherDataPanel.reloadData();
           roomDataPanel.reloadData();
        });

        csvItem.addActionListener(action -> {
            var fc = new JFileChooser();
            fc.showOpenDialog(this);
            var result = fc.getSelectedFile();
            if (result != null)
            {
                for (var student : new CsvParser().parseFromFile(result)) {
                    DaoConstants.STUDENT.save(student.toStudent());
                }
            }
        });

        setResizable(false);
    }
}
