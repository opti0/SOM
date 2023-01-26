package som;

import com.formdev.flatlaf.FlatLightLaf;
import som.client.csv.CsvParser;
import som.client.gui.Window;
import som.server.database.DaoConstants;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception ignored) {}
            new Window(1000, 600);
        });
    }
}
