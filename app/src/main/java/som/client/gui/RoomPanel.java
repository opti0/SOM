package som.client.gui;

import som.client.utils.Utils;
import som.server.database.DaoConstants;
import som.server.database.room.Room;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RoomPanel extends JScrollPane {
    private List<Room> rooms = new ArrayList<>();
    private final JPanel dataPanel;

    private static final int NUMBER_OF_FIELDS = 2;

    public RoomPanel(JPanel dataPanel) {
        super(dataPanel);
        this.dataPanel = dataPanel;
        reloadData();
    }

    public void reloadData() {
        rooms = DaoConstants.ROOM.getAll();
        buildTableFromData();
    }

    public void buildTableFromData() {
        dataPanel.setLayout(new GridLayout(0, NUMBER_OF_FIELDS));
        createHeader();
        createBody();
    }

    private void createHeader() {
        dataPanel.add(new JLabel(Utils.toUtf8("Numer sali"), SwingConstants.CENTER));
        dataPanel.add(new JLabel("Liczba miejsc", SwingConstants.CENTER));
    }

    private void createBody() {
        for (var room : rooms) {
            createRoomField(room);
        }
    }

    private void createRoomField(Room room) {
        dataPanel.add(new JLabel(Utils.toUtf8(room.roomNumber()), SwingConstants.CENTER));
        dataPanel.add(new JLabel(Integer.toString(room.numberOfSeats()), SwingConstants.CENTER));
    }
}
