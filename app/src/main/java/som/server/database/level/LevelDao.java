package som.server.database.level;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LevelDao implements Dao<Level, String> {
    private final LevelParser levelParser = new LevelParser();

    @Override
    public Optional<Level> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(levelParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, levelParser);
            var level = generator.getNext();

            statement.close();
            connection.close();

            return level;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Level> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(levelParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, levelParser);

            List<Level> levels = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(levels::add);
            }

            statement.close();
            connection.close();

            return levels;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Level level) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(levelParser.createInsertQuery(level));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(Level level, String[] params) {

    }

    @Override
    public void delete(Level level) {

    }
}
