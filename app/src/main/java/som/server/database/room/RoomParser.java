package som.server.database.room;

import som.server.database.SqlParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoomParser implements SqlParser<Room, String> {
    public String createInsertQuery(Room room) {
        return "INSERT INTO Sala VALUES ('" +
                "null" + "', '" +
                room.roomNumber() + "', '" +
                room.numberOfSeats() +"');";
    }

    @Override
    public String createGetQuery(String key) {
        return String.format("SELECT * FROM Sala WHERE ID_Sali=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Sala;";
    }

    @Override
    public String createSaveQuery(Room room) {
        return "INSERT INTO Sala VALUES ('" +
                "null" + "', '" +
                room.roomNumber() + "', '" +
                room.numberOfSeats() +"');";
    }

    @Override
    public String createUpdateQuery(Room room, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(Room room) {
        return null;
    }

    @Override
    public Optional<Room> makeFrom(ResultSet result) {
        try {
            return Optional.of(new Room(
                    result.getInt("ID_Sali"),
                    result.getString("Nr_Sali"),
                    result.getInt("Liczba_Miejsc")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
