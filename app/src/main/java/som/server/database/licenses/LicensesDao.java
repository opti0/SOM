package som.server.database.licenses;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LicensesDao implements Dao<Licenses, String> {
    private final LicensesParser licensesParser = new LicensesParser();

    @Override
    public Optional<Licenses> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(licensesParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, licensesParser);
            var licenses = generator.getNext();

            statement.close();
            connection.close();

            return licenses;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Licenses> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(licensesParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, licensesParser);

            List<Licenses> licenses = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(licenses::add);
            }

            statement.close();
            connection.close();

            return licenses;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Licenses licenses) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(licensesParser.createInsertQuery(licenses));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(Licenses licenses, String[] params) {

    }

    @Override
    public void delete(Licenses licenses) {

    }
}
