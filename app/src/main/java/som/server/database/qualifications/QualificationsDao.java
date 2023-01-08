package som.server.database.qualifications;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QualificationsDao implements Dao<Qualifications, String> {
    private final QualificationsParser qualificationsParser = new QualificationsParser();

    @Override
    public Optional<Qualifications> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(qualificationsParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, qualificationsParser);
            var qualifications = generator.getNext();

            statement.close();
            connection.close();

            return qualifications;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Qualifications> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(qualificationsParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, qualificationsParser);

            List<Qualifications> qualifications = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(qualifications::add);
            }

            statement.close();
            connection.close();

            return qualifications;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Qualifications qualifications) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(qualificationsParser.createInsertQuery(qualifications));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(Qualifications qualifications, String[] params) {

    }

    @Override
    public void delete(Qualifications qualifications) {

    }
}
