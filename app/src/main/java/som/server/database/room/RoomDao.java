package som.server.database.room;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDao implements Dao<Room, String> {
    private final RoomParser roomParser = new RoomParser();

    @Override
    public Optional<Room> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(roomParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, roomParser);
            var room = generator.getNext();

            statement.close();
            connection.close();

            return room;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Room> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(roomParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, roomParser);

            List<Room> rooms = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(rooms::add);
            }

            statement.close();
            connection.close();

            return rooms;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Room room) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(roomParser.createInsertQuery(room));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(Room room, String[] params) {

    }

    @Override
    public void delete(Room room) {

    }
}
