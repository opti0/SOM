package som.client.gui;

import som.client.utils.Utils;
import som.server.database.DatabaseData;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    public SettingsPanel(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        var dbUrl = new JTextField(DatabaseData.DB_URL.substring(DatabaseData.DB_URL.indexOf("://") + 3));
        var dbUser = new JTextField(DatabaseData.DB_USER);
        var dbPassword = new JTextField();

        add(new JLabel("Adres bazy danych "));
        add(dbUrl);
        add(new JLabel(Utils.toUtf8("Nazwa użytkownika:")));
        add(dbUser);
        add(new JLabel(Utils.toUtf8("Hasło:")));
        add(dbPassword);

        var applyButton = new JButton("Apply");
        add(applyButton);

        applyButton.addActionListener(e -> {
            DatabaseData.DB_URL = "jdbc:mysql://" + dbUrl.getText();
            DatabaseData.DB_USER = dbUser.getText();
            DatabaseData.DB_PASSWORD = dbPassword.getText();
        });
    }
}
