package som.server.database.exam_subject;

import som.server.database.Dao;
import som.server.database.DatabaseData;
import som.server.database.ObjectGenerator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamSubjectDao implements Dao<ExamSubject, String> {
    private final ExamSubjectParser examSubjectParser = new ExamSubjectParser();

    @Override
    public Optional<ExamSubject> get(String key) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(examSubjectParser.createGetQuery(key));
            var generator = new ObjectGenerator<>(result, examSubjectParser);
            var examSubject = generator.getNext();

            statement.close();
            connection.close();

            return examSubject;
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ExamSubject> getAll() {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            var result = statement.executeQuery(examSubjectParser.createGetAllQuery());
            var generator = new ObjectGenerator<>(result, examSubjectParser);

            List<ExamSubject> examSubjects = new ArrayList<>();

            while (generator.hasNext()) {
                generator.getNext().ifPresent(examSubjects::add);
            }

            statement.close();
            connection.close();

            return examSubjects;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(ExamSubject subject) {
        try {
            var connection = DriverManager.getConnection(DatabaseData.DB_URL, DatabaseData.DB_USER, DatabaseData.DB_PASSWORD);
            var statement = connection.createStatement();
            statement.executeUpdate(examSubjectParser.createInsertQuery(subject));

            statement.close();
            connection.close();
        } catch (SQLException ignored) {

        }
    }

    @Override
    public void update(ExamSubject subject, String[] params) {

    }

    @Override
    public void delete(ExamSubject subject) {

    }
}
