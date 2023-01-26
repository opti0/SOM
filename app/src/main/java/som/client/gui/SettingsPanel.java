package som.client.gui;

import som.server.database.DatabaseData;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    JLabel dbLinkL = new JLabel("Link do bazy danych");
    JTextField dbLink = new JTextField();
    JButton applyButton = new JButton("Apply");

    public SettingsPanel(){
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(dbLinkL);
        add(dbLink);
        add(applyButton);

        applyButton.addActionListener(e -> DatabaseData.DB_URL = dbLink.getText());
    }
}
