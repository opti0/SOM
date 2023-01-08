package som.server.database.jury_members;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;
import som.server.database.jury.Jury;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JuryMembersDao implements Dao<JuryMembers, String> {
    private final JuryMembersParser juryMembersParser = new JuryMembersParser();

    @Override
    public Optional<JuryMembers> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(juryMembersParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, juryMembersParser);
            var jurymembers = generator.getNext();

            statement.close();
            connection.close();

            return jurymembers;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<JuryMembers> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(juryMembersParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, juryMembersParser);

            List<JuryMembers> juryMembers = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(juryMembers::add);
            }

            statement.close();
            connection.close();

            return juryMembers;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(JuryMembers juryMembers) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(juryMembersParser.createInsertQuery(juryMembers));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(JuryMembers juryMembers, String[] params) {

    }

    @Override
    public void delete(JuryMembers j) {

    }
}
