package som.server.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Dao<T, K> {

    private final SqlParser<T, K> parser;

    public Dao(SqlParser<T, K> parser) {
        this.parser = parser;
    }

    public Optional<T> get(K key) {
        try {
            var connection = DriverManager.getConnection(
                    DatabaseData.DB_URL,
                    DatabaseData.DB_USER,
                    DatabaseData.DB_PASSWORD
            );
            var statement = connection.createStatement();
            var result = statement.executeQuery(parser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, parser);
            var exam = generator.getNext();

            statement.close();
            connection.close();

            return exam;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    public List<T> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(parser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, parser);

            List<T> objects = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(objects::add);
            }

            statement.close();
            connection.close();

            return objects;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public void save(T t) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(parser.createSaveQuery(t));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    public void update(T t, String[] params) {

    }

    public void delete(T t) {

    }
}