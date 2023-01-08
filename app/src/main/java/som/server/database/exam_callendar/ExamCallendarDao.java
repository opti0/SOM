package som.server.database.exam_callendar;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamCallendarDao implements Dao<ExamCallendar, String> {
    private final ExamCallendarParser examCallendarParser = new ExamCallendarParser();

    @Override
    public Optional<ExamCallendar> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(examCallendarParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, examCallendarParser);
            var examCallendar = generator.getNext();

            statement.close();
            connection.close();

            return examCallendar;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ExamCallendar> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(examCallendarParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, examCallendarParser);

            List<ExamCallendar> examCallendars = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(examCallendars::add);
            }

            statement.close();
            connection.close();

            return examCallendars;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(ExamCallendar examCallendar) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(examCallendarParser.createInsertQuery(examCallendar));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(ExamCallendar examCallendar, String[] params) {

    }

    @Override
    public void delete(ExamCallendar examCallendar) {

    }
}
