package som.server.database.exam;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamDao implements Dao<Exam, String> {
    private final ExamParser examParser = new ExamParser();

    @Override
    public Optional<Exam> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(examParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, examParser);
            var exam = generator.getNext();

            statement.close();
            connection.close();

            return exam;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Exam> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(examParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, examParser);

            List<Exam> exams = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(exams::add);
            }

            statement.close();
            connection.close();

            return exams;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Exam exam) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(examParser.createInsertQuery(exam));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(Exam exam, String[] params) {

    }

    @Override
    public void delete(Exam exam) {

    }
}
