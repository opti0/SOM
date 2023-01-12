package som;

import som.client.csv.CsvParser;
import som.client.gui.Window;
import som.server.database.DaoConstants;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class App {

    public static void main(String[] args) {
        try {
            var result = new CsvParser().parseFromFile(new File(
                    Objects.requireNonNull(App.class.getClassLoader().getResource("students.csv")).toURI()
            ));
            for (var student : result) {
                DaoConstants.STUDENT.save(student.toStudent());
            }
        } catch (URISyntaxException e) {
            System.out.println("Nie załadowano pliku");
        }

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException ignored) {
            }
            new Window(1000, 600);
        });
    }
}
