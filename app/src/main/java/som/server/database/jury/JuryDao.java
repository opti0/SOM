package som.server.database.jury;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JuryDao implements Dao<Jury, String> {
    private final JuryParser studentParser = new JuryParser();

    @Override
    public Optional<Jury> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(studentParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, studentParser);
            var jury = generator.getNext();

            statement.close();
            connection.close();

            return jury;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Jury> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(studentParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, studentParser);

            List<Jury> juries = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(juries::add);
            }

            statement.close();
            connection.close();

            return juries;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Jury jury) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(studentParser.createInsertQuery(jury));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(Jury jury, String[] params) {

    }

    @Override
    public void delete(Jury jury) {

    }
}
